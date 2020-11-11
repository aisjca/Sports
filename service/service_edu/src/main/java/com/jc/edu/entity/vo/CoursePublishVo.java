package com.jc.edu.entity.vo;

import lombok.Data;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/22 19:50
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示


}
