package com.jc.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.Teacher;
import com.jc.edu.service.CourseService;
import com.jc.edu.service.TeacherService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/10 21:05
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;


    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public Result index() {
        //查询前4条名师
        List<Teacher> teacherList = teacherService.getFourTeacher();

        //查询前8条热门课程
        List<Course> courseList = courseService.getEightCourse();

        return Result.ok().data("teacherList", teacherList)
                .data("courseList", courseList);
    }
}
