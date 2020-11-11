package com.jc.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/09 22:00
 */
@SpringBootApplication
@ComponentScan({"com.jc"})
@MapperScan("com.jc.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
