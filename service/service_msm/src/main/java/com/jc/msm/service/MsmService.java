package com.jc.msm.service;

import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/13 21:53
 */
public interface MsmService {
    Boolean send(Map<String, Object> param, String phone);
}
