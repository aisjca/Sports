package com.jc.edu.client.impl;

import com.jc.edu.client.VodClient;
import com.jc.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program sport_parent
 * @description: 实现VodClient的方法，当VodClient方法报错的时候，会熔断服务，执行该类的实现方法
 * @author: JC
 * @create: 2020/10/07 00:17
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错后会执行
    @Override
    public Result removeAlyiVideo(String id) {
        return Result.error().message("删除视频失败");
    }

    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.error().message("删除多个视频失败");
    }
}
