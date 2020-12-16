package com.jc.order.client;

import com.jc.utils.Result;
import com.jc.utils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program sport_parent
 * @description:返回课程信息
 * @author: JC
 * @create: 2020/11/16 21:01
 */
@FeignClient(name = "service-edu")
@Component
public interface EduClient {

    //课程详情方法
    @GetMapping("/eduservice/coursefront/getCourseInfoOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("courseId") String courseId);


}
