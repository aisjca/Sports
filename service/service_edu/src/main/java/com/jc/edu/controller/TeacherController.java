package com.jc.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.base.exceptionhandler.SportException;
import com.jc.edu.entity.Teacher;
import com.jc.edu.entity.vo.TeacherQuery;
import com.jc.edu.service.TeacherService;
import com.jc.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-09-04
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //1查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items", list);
    }

    //2 逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag == true ? Result.ok() : Result.error();
    }

    /**
     * 分页查询讲师
     *
     * @param current 当前页
     * @param limit   每页最大页数
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师")
    public Result pageListTeacher(@PathVariable long current, @PathVariable long limit) {
        Page<Teacher> pageTeacher = new Page<>(1, 3);
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new SportException(201,"执行自定义异常");
        }
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        return Result.ok().data("total", total).data("rows", records);
    }

    //4 条件查询带分页的方法
    //RequestBody提交数据，把对应数据封装在对应对象里面，但是需要用@PostMapping 才行
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "条件查询带分页查找讲师")
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<Teacher> pageTeacher = new Page<>(current, limit);
        //构造条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //构造条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //实现条件查询
        teacherService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        return Result.ok().data("total", total).data("rows", records);
    }

    //添加讲师接口
    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public Result addTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        return save == true ? Result.ok() : Result.error();
    }

    //根据讲师Id进行查询
    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据Id查询讲师")
    public Result getTeacher(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    @ApiOperation(value = "修改讲师")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean flag = teacherService.updateById(teacher);
        return flag == true ? Result.ok() : Result.error();
    }
}

