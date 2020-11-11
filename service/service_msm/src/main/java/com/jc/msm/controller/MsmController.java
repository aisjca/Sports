package com.jc.msm.controller;

import com.jc.msm.service.MsmService;
import com.jc.msm.utils.RandomUtil;
import com.jc.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/13 21:53
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //从redis获取验证码，取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok().data("code", code);
        }

        //取不到，利用阿里云发送重新缓存
        //生成随机值，传给阿里云，让阿里云发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        Boolean isSend = msmService.send(param, phone);
        if (isSend) {
            //发送成功，缓存验证码
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("短信发送失败");
        }

    }
}
