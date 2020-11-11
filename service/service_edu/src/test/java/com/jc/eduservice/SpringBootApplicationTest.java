package com.jc.eduservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/03 23:48
 */
@SpringBootTest(classes = SpringBootApplicationTest.class)
@RunWith(SpringRunner.class)
public class SpringBootApplicationTest {

    @Test
    public void hello() {
        System.out.println("hello");
    }
}
