package com.jc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @program sport_parent
 * @description: json转换工具类
 * @author: JC
 * @create: 2020/11/15 15:41
 */
public class JsonUtil {

    //把对象序列化称String
    public static String JsonToString(Object value) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(value);
    }

    //把String系列化成对象
    public static Object StringToJson(String value,Class clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(value, clazz);
    }

}
