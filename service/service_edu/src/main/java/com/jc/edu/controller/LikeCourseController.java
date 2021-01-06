package com.jc.edu.controller;


import com.jc.edu.service.LikeCourseService;
import com.jc.utils.RedisUtils;
import com.jc.utils.Result;
import com.jc.utils.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author jc
 * @@description 用户点赞功能的前端控制器
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/eduservice/likeCourse")
public class LikeCourseController {

    @Autowired
    private LikeCourseService likeCourseService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param courseId 课程Id
     * @param userId   用户Id
     * @return
     * @description 用户点赞
     */
    @PostMapping("likeCourse/{courseId}/{userId}")
    public Result likeCourse(@PathVariable String courseId, @PathVariable String userId) {
        boolean isLike = likeCourseService.validateParam(courseId, userId);//判断用户是否已经点赞了，true为还没点赞
        if (isLike) {
            likeCourseService.likeCourse(courseId,userId);//点赞
            return Result.ok().data("totalLikeCount", stringRedisTemplate.opsForValue().get(RedisConstant.getCourseLikeHashKey(courseId)));
        } else {
            return Result.error().message("用户已经点赞了");
        }

    }

    /**
     * @param courseId 课程Id
     * @param userId   用户Id
     * @return
     * @description 用户取消点赞
     */
    @PostMapping("unlikeCourse/{courseId}/{userId}")
    public Result unlikeCourse(@PathVariable String courseId, @PathVariable String userId) {
        return Result.error();
    }

}

