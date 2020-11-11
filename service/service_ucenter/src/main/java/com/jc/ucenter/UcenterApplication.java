package com.jc.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/14 14:52
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jc"})
@MapperScan("com.jc.ucenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
