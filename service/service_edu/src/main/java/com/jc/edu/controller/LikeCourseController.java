package com.jc.edu.controller;


import com.jc.edu.service.LikeCourseService;
import com.jc.utils.RedisUtils;
import com.jc.utils.Result;
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
        int totalLikeCount = Integer.parseInt((String) stringRedisTemplate.opsForHash().get("totalLikeCount", courseId));//获取当前总点赞数
        if (isLike) {
            totalLikeCount += 1;//不是原子操作，有线程安全问题
            stringRedisTemplate.opsForHash().put("totalLikeCount", courseId, String.valueOf(totalLikeCount));
            return Result.ok().data("totalLikeCount", String.valueOf(totalLikeCount));
        } else {//如果已经点赞了，则不能重复点赞
            return Result.error();
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
        boolean isLike = likeCourseService.validateParam(courseId, userId);//判断用户是否已经点赞了，true为还没点赞
        int totalLikeCount = Integer.parseInt((String) stringRedisTemplate.opsForHash().get("totalLikeCount", courseId));//获取当前总点赞数
        if (isLike) {//如果还没赞，则不能取消点赞
            return Result.error();
        } else {//如果已经点赞了，则可以取消点赞
            totalLikeCount -= 1;//不是原子操作，有线程安全问题
            stringRedisTemplate.opsForHash().put("totalLikeCount", courseId, String.valueOf(totalLikeCount));
            return Result.ok().data("totalLikeCount", totalLikeCount);
        }
    }

}

