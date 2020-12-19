package com.jc.edu.service;

import com.jc.edu.entity.LikeCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @description 用户点赞功能的服务类
 * @author jc
 * @since 2020-12-16
 */
public interface LikeCourseService extends IService<LikeCourse> {

    boolean validateParam(String courseId, String userId);

    void updateCourseLike(String courseId, Integer totalLikeCount);
}
