package com.jc.edu.controller;


import com.jc.edu.entity.subject.OneSubject;
import com.jc.edu.service.SubjectService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public Result getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Result.ok().data("list",list);
    }
}

