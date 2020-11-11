package com.jc.edu.service;

import com.jc.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(String courseId);
}
