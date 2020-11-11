package com.jc.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program sport_parent
 * @description: 一级分类
 * @author: JC
 * @create: 2020/09/14 22:17
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    //一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
