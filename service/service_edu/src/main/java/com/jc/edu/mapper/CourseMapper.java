package com.jc.edu.mapper;

import com.jc.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.edu.entity.frontvo.CourseWebVo;
import com.jc.edu.entity.vo.CourseInfoVo;
import com.jc.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
public interface CourseMapper extends BaseMapper<Course> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);

}
