package com.jc.statistics.controller;


import com.jc.statistics.service.DailyService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {

    @Autowired
    private DailyService dailyService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day) {
        dailyService.registerCount(day);
        return Result.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type, @PathVariable String begin,
                           @PathVariable String end) {
        Map<String, Object> map = dailyService.showData(type, begin, end);
        return Result.ok().data(map);
    }
}

