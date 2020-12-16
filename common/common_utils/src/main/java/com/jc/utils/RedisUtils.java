package com.jc.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/15 15:20
 */
@Configuration
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description:
     * @Param
     * name 字典里面的key
     * key 哈希表的key
     * value 哈希表的值
     * @date: 2020/11/17
     */
    public void HsetValue(String name, String key, Object value) {
        stringRedisTemplate.boundHashOps(name).put(key, JsonUtil.JsonToString(value));
        stringRedisTemplate.expire(name, 10, TimeUnit.MINUTES);
    }

    public Object HgetValue(String name, String key, Class clazz) {
        return JsonUtil.StringToJson((String) stringRedisTemplate.boundHashOps(name).get(key), clazz);
    }
}
