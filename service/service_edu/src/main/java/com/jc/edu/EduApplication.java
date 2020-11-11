package com.jc.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/04 10:43
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jc"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.jc.edu.mapper")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
