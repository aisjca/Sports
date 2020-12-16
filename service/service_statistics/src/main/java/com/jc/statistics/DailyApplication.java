package com.jc.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program sport_parent
 * @description: 统计分析启动类
 * @author: JC
 * @create: 2020/12/01 00:34
 */
@SpringBootApplication
@MapperScan("com.jc.statistics.mapper")
@EnableFeignClients
@ComponentScan("com.jc")
@EnableDiscoveryClient
@EnableScheduling//开启定时任务
public class DailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
    }
}
