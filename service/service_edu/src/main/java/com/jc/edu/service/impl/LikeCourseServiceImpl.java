package com.jc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.LikeCourse;
import com.jc.edu.mapper.LikeCourseMapper;
import com.jc.edu.service.CourseService;
import com.jc.edu.service.LikeCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-12-16
 */
@Service
public class LikeCourseServiceImpl extends ServiceImpl<LikeCourseMapper, LikeCourse> implements LikeCourseService {

    @Autowired
    private CourseService courseService;
    /**
     * 判断用户是否已经点赞过了
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public boolean validateParam(String courseId, String userId) {
        QueryWrapper<LikeCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("course_id", courseId);
        LikeCourse likeCourse = baseMapper.selectOne(wrapper);
        if (likeCourse == null) {//还没点赞
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新课程点赞数量
     * @param courseId
     * @param totalLikeCount
     */
    @Override
    public void updateCourseLike(String courseId, Integer totalLikeCount) {
        baseMapper.updateCourseLike(courseId, totalLikeCount);
    }
}
