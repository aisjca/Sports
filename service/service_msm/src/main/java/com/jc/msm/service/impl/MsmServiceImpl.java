package com.jc.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jc.msm.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/10/13 21:53
 */
@Service
public class MsmServiceImpl implements MsmService {

    //发送短信
    @Override
    public Boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4G7QSQjyJETHLUnCh8pH", "D5m2Hq56n7vqUhdehADIyBASUwYi0N");
        IAcsClient client = new DefaultAcsClient(profile);
        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone); //手机号
        request.putQueryParameter("SignName", "我的健身在线教育网站");//申请阿里云 签名名称
        request.putQueryParameter("TemplateCode", "SMS_204460046");//申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，转换json数据传递

        try{
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
