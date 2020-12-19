package com.jc.edu.schedule;

import com.jc.edu.service.LikeCourseService;
import com.jc.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/12/16 00:28
 */
@Component
public class ScheduleTask {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LikeCourseService likeCourseService;
    /**
     * 每小时执行一次，把redis的内容持久化到数据库
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void redisTotalLikeAccountToMysql() {
        System.out.println("schedule is run");
        //返回所有的课程的总点赞数，持久化到数据库，map的key为课程ID，value为对应的课程总点赞数
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("totalLikeCount");
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            String courseId = (String) entry.getKey();
            int totalLikeCount = (int) entry.getValue();
            likeCourseService.updateCourseLike(courseId, totalLikeCount);//更新课程点赞数
        }
        System.out.println("schedule is end");

    }
}
