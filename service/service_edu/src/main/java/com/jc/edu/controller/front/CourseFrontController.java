package com.jc.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.edu.client.OrderClient;
import com.jc.edu.entity.Course;
import com.jc.edu.entity.chapter.ChapterVo;
import com.jc.edu.entity.frontvo.CourseFrontVo;
import com.jc.edu.entity.frontvo.CourseWebVo;
import com.jc.edu.service.ChapterService;
import com.jc.edu.service.CourseService;
import com.jc.utils.JwtUtils;
import com.jc.utils.RedisUtils;
import com.jc.utils.Result;
import com.jc.utils.constant.RedisConstant;
import com.jc.utils.ordervo.CourseWebVoOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/02 22:39
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<Course> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(courseFrontVo, pageCourse);
        return Result.ok().data(map);
    }


    /**
     * @description:课程详情方法
     */
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        //根据courseId和memberId来查询用户是否购买了课程
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (memberId == null||memberId.equals("")) {
            return Result.ok().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVideoList);
        }
        //判断用户是否已经购买课程
        boolean isBuy = orderClient.getOrderState(courseId, memberId);
        int totalLikeCount = 0;
        //判断点赞数量是否已经加入缓存了
        if (stringRedisTemplate.opsForValue().get(RedisConstant.getCourseLikeHashKey(courseId)) == null) {
            totalLikeCount = courseWebVo.getTotalLikeCount();//总点赞数
            stringRedisTemplate.opsForValue().set(RedisConstant.getCourseLikeHashKey(courseId), String.valueOf(totalLikeCount));
        } else {
            totalLikeCount = Integer.parseInt((String) stringRedisTemplate.opsForValue().get(RedisConstant.getCourseLikeHashKey(courseId)));
        }
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVideoList).data("isBuy", isBuy)
                .data("totalLikeCount", totalLikeCount);
    }

    //获得课程详情方法
    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String courseId) {
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
