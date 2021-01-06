package com.jc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.LikeCourse;
import com.jc.edu.mapper.LikeCourseMapper;
import com.jc.edu.service.CourseService;
import com.jc.edu.service.LikeCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.utils.Result;
import com.jc.utils.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户redis的set来判断用户是否已经点赞了
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public boolean validateParam(String courseId, String userId) {
        //判断用户是否已经点赞
        boolean isMember = stringRedisTemplate.opsForSet().isMember(RedisConstant.getCourseLikeSetKey(courseId), userId);
        if (isMember) {//如果用户已经点赞
            return false;
        }else{
            //把userId加入set
            stringRedisTemplate.opsForSet().add(RedisConstant.getCourseLikeSetKey(courseId), userId);
            return true;
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

    @Override
    public void likeCourse(String courseId,String userId) {
        String totalLikeCount = stringRedisTemplate.opsForValue().get(RedisConstant.getCourseLikeHashKey(courseId));//获取课程当前总点赞数
        if (totalLikeCount == null || totalLikeCount.equals("0")) {
            //若缓存没有，那么就查数据库,并且点赞数加1
            Course course = courseService.getById(courseId);
            totalLikeCount = String.valueOf(course.getTotalLikecount() + 1);
            LikeCourse likeCourse = new LikeCourse();
            //用户对应的点赞记录插入数据库
            likeCourse.setCourseId(courseId);
            likeCourse.setUserId(userId);
            likeCourse.setDeleted(0);
            baseMapper.insert(likeCourse);
            stringRedisTemplate.opsForValue().set(RedisConstant.getCourseLikeHashKey(courseId), String.valueOf(totalLikeCount), 1, TimeUnit.HOURS);
        } else {
            //若缓存有该课程点赞总数
            stringRedisTemplate.opsForValue().increment(RedisConstant.getCourseLikeHashKey(courseId), 1);
        }
    }
}
