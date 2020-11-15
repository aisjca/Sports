package com.jc.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.edu.entity.frontvo.CourseFrontVo;
import com.jc.edu.entity.frontvo.CourseWebVo;
import com.jc.edu.entity.vo.CourseInfoVo;
import com.jc.edu.entity.vo.CoursePublishVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
public interface CourseService extends IService<Course> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo PublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    List<Course> getEightCourse();

    Map<String, Object> getCourseFrontList(CourseFrontVo courseFrontVo, Page<Course> pageCourse);

    CourseWebVo getBaseCourseInfo(String courseId);
}
