package com.jc.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.statistics.client.UcenterClient;
import com.jc.statistics.entity.Daily;
import com.jc.statistics.mapper.DailyMapper;
import com.jc.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.utils.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-12-01
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 统计某一天注册人数,生成统计数据
     * @param day 日期
     */
    @Override
    public void registerCount(String day) {
        //添加记录之前删除表相同日期的数据
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);
        Result result = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) result.getData().get("countRegister");
        //把获取数据添加数据库，统计分析表里面
        Daily daily = new Daily();
        daily.setRegisterNum(countRegister); //注册人数
        daily.setDateCalculated(day);//统计日期
        daily.setVideoViewNum(RandomUtils.nextInt(100, 200));//当日观看视频人数
        daily.setCourseNum(RandomUtils.nextInt(100, 200));//当日观看课程人数
        daily.setLoginNum(RandomUtils.nextInt(100, 200));//当日登陆人数
        baseMapper.insert(daily);
    }

    /**
     *  图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type 查询类型
     * @param begin 开始日期
     * @param end 结束日期
     * @return
     */
    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        //根据条件查询对应数据
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<Daily> dailyList = baseMapper.selectList(wrapper);
        Map<String, Object> map = new HashMap<>();
        List<Integer> numDataList = new ArrayList<>();
        List<String> dateCalculatedList = new ArrayList<>();
        for (int i = 0; i < dailyList.size(); i++) {
            Daily daily = dailyList.get(i);
            dateCalculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        map.put("date_calculatedList", dateCalculatedList);
        map.put("numDataList", numDataList);
        return map;
    }
}
