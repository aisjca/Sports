package com.jc.edu.mapper;

import com.jc.edu.entity.LikeCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jc
 * @since 2020-12-16
 */
public interface LikeCourseMapper extends BaseMapper<LikeCourse> {

    void updateCourseLike(@Param("courseId") String courseId,@Param("totalLikeCount") Integer totalLikeCount);
}
