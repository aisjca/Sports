package com.jc.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.edu.entity.Subject;
import com.jc.edu.entity.excel.SubjectData;
import com.jc.edu.entity.subject.OneSubject;
import com.jc.edu.entity.subject.TwoSubject;
import com.jc.edu.listener.SubjectExcelListener;
import com.jc.edu.mapper.SubjectMapper;
import com.jc.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-09-13
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1 查询所有一级分类  parentid = 0
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", 0);
        List<Subject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //1 查询所有二级分类  parentid ！= 0
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", 0);
        List<Subject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        List<OneSubject> findAllSubjectList = new ArrayList<>();
        for (Subject one : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(one, oneSubject);
            findAllSubjectList.add(oneSubject);
            List<TwoSubject> twoSubjects = new ArrayList<>();
            for (Subject two : twoSubjectList) {
                if (two.getParentId().equals(one.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(two, twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjects);
        }
        return findAllSubjectList;
    }
}
