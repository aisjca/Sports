package com.jc.statistics.service;

import com.jc.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author jc
 * @since 2020-12-01
 */
public interface DailyService extends IService<Daily> {

    //统计某一天注册人数,生成统计数据
    void registerCount(String day);

    Map<String, Object> showData(String type, String begin, String end);
}
