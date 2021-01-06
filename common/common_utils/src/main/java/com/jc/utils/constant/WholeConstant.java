package com.jc.utils.constant;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program sport_parent
 * @description:全局变量
 * @author: JC
 * @create: 2021/01/07 00:44
 */
public class WholeConstant {

    //userId对应，改用户点赞的课程
    private HashMap<String, HashSet<String>> userIdACourIdsSet = new HashMap<>();

    public HashMap<String, HashSet<String>> getUserIdACourIdsSet() {
        return userIdACourIdsSet;
    }
}
