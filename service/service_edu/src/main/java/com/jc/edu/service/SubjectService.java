package com.jc.edu.service;

import com.jc.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jc
 * @since 2020-09-13
 */
public interface SubjectService extends IService<Subject> {

    //添加课程分类
    void saveSubject(MultipartFile file, SubjectService subjectService);

    //课程分类列表(树形)
    public List<OneSubject> getAllOneTwoSubject();
}
