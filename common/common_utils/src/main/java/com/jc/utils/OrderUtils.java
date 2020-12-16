package com.jc.utils;

import io.swagger.models.auth.In;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @program sport_parent
 * @description: 生产订单号
 * @author: JC
 * @create: 2020/11/16 21:33
 */
public class OrderUtils {
    /**
     * @description: 订单前14位位当前时间，后5位为随机数，这样有可能导致orderNo重复，后期用雪花算法改进
     */
    public static String CreateOrderNo() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer sb = new StringBuffer();
        sb.append(simpleDateFormat.format(date));
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            sb.append(random.nextInt(10));
        }

        //TODO 实现雪花算法
        return sb.toString();
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(CreateOrderNo());
                }
            }).start();
        }
        //利用生成10000个OrderNo的时候，发现输出set的size平均为9500，证明重复了500个ID
        System.out.println(set.size());

    }
}
