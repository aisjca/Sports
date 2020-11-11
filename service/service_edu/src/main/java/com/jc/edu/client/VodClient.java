package com.jc.edu.client;

import com.jc.edu.client.impl.VodFileDegradeFeignClient;
import com.jc.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/01 11:03
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {


    //定义方法调用路径
    //根据视频ID删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAlyiVideo/{id}")
    public Result removeAlyiVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
