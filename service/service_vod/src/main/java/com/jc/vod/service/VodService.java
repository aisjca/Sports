package com.jc.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/26 21:55
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}
