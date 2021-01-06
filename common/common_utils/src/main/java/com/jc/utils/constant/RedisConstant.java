package com.jc.utils.constant;

/**
 * @program sport_parent
 * @description:redis缓存常量
 * @author: JC
 * @create: 2020/12/30 23:31
 */
public class RedisConstant {

    //课程详情缓存key
    public static String getCourseInfoKey(String courseId) {
        return "courseInfo: " + courseId;
    }

    //判断用户是否已经点赞,key为set:courseLike:+courseId，value对应用户set集合
    public static String getCourseLikeSetKey(String courseId) {
        return "set:courseLike: " + courseId;
    }

    //获取用户点赞数量,key为hash:courseLike:+courseId，value对应课程点赞数量
    public static String getCourseLikeHashKey(String courseId) {
        return "hash:courseLike: " + courseId;
    }

    //每个课程对应的评论缓存key
    public static String getCourseCommentKey(String courseId) {
        return "CourseComment-" + courseId;
    }

    //把用户操作过的course，例如点赞，收藏，存进list缓存，方便定时器持久化存储
    public static String getCourseList() {
        return "courseIds:";
    }

}
