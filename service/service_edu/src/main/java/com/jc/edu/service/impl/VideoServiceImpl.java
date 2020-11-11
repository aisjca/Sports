package com.jc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.edu.client.VodClient;
import com.jc.edu.entity.Video;
import com.jc.edu.mapper.VideoMapper;
import com.jc.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    @Transactional
    public void removeVideoByCourseId(String courseId) {
        //删除阿里云多个视频
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        videoWrapper.select("video_source_id");
        List<Video> videoList = baseMapper.selectList(videoWrapper);
        //把List<Video> videoList变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        }
        //删除所有courseId下的阿里云视频
        if (videoIds.size() > 0) {
            vodClient.deleteBatch(videoIds);
        }
        //删除数据库的courseId课程
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
