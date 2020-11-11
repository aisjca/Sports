package com.jc.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jc
 * @since 2020-09-04
 */
public interface TeacherService extends IService<Teacher> {
    List<Teacher> getFourTeacher();

    Map<String, Object> getTeacherFrontList(Page<Teacher> pageTeacher);
}
