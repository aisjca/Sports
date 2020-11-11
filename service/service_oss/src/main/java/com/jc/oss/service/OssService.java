package com.jc.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/11 16:33
 */
public interface OssService {
    //上传文件到oss
    String uploadFileAvatar(MultipartFile file);

}
