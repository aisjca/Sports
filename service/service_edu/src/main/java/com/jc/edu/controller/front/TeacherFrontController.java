package com.jc.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.Teacher;
import com.jc.edu.service.CourseService;
import com.jc.edu.service.TeacherService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/23 10:13
 */
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
@RestController
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;


    //1 分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable Long page, @PathVariable Long limit) {
        Page<Teacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return Result.ok().data(map);
    }

    //讲师详情的方法
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId) {
        //1 根据讲师id查询讲师基本信息
        Teacher teacher = teacherService.getById(teacherId);
        //2 根据讲师id查询所讲课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<Course> courseList = courseService.list(wrapper);
        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
