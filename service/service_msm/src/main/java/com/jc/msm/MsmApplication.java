package com.jc.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/13 21:52
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//不加载数据库
@ComponentScan("com.jc")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
