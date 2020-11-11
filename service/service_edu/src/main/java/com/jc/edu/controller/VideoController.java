package com.jc.edu.controller;


import com.jc.edu.client.VodClient;
import com.jc.edu.entity.Video;
import com.jc.edu.service.VideoService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;


    //添加小节
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody Video video) {
        videoService.save(video);
        return Result.ok();
    }

    //删除小节
    // 删除小节的时候同时删除视频
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        //根据小节id得到视频id
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        //判断小节里面是否有视频
        if (videoSourceId != null) {
            //根据视频ID，远程删除阿里云视频
            vodClient.removeAlyiVideo(videoSourceId);
        }
        //删除小节
        boolean flag = videoService.removeById(id);
        return flag == true ? Result.ok() : Result.error();
    }

    //修改小节
    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody Video video) {
        boolean flag = videoService.updateById(video);
        return flag == true ? Result.ok() : Result.error();
    }
}

