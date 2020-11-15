package com.jc.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.chapter.ChapterVo;
import com.jc.edu.entity.frontvo.CourseFrontVo;
import com.jc.edu.entity.frontvo.CourseWebVo;
import com.jc.edu.service.ChapterService;
import com.jc.edu.service.CourseService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/02 22:39
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<Course> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(courseFrontVo, pageCourse);
        return Result.ok().data(map);
    }


    //课程详情方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVideoList);
    }

}
