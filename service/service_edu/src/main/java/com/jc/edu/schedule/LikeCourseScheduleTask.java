package com.jc.edu.schedule;

import com.jc.edu.service.LikeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program sport_parent
 * @description: 定时把用户点赞课程存入数据库
 * @author: JC
 * @create: 2021/01/06 22:47
 */
@Component
public class LikeCourseScheduleTask {
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
