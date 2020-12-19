package com.jc.edu.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/02 22:37
 */
public class CourseWebVo{

    private String id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String intro;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "课程一级类别ID")
    private String subjectLevelOneId;

    @ApiModelProperty(value = "类别一级名称")
    private String subjectLevelOne;

    @ApiModelProperty(value = "课程二级类别ID")
    private String subjectLevelTwoId;

    @ApiModelProperty(value = "类别二级名称")
    private String subjectLevelTwo;

    @ApiModelProperty(value = "课程点赞总数")
    private int totalLikeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSubjectLevelOneId() {
        return subjectLevelOneId;
    }

    public void setSubjectLevelOneId(String subjectLevelOneId) {
        this.subjectLevelOneId = subjectLevelOneId;
    }

    public String getSubjectLevelOne() {
        return subjectLevelOne;
    }

    public void setSubjectLevelOne(String subjectLevelOne) {
        this.subjectLevelOne = subjectLevelOne;
    }

    public String getSubjectLevelTwoId() {
        return subjectLevelTwoId;
    }

    public void setSubjectLevelTwoId(String subjectLevelTwoId) {
        this.subjectLevelTwoId = subjectLevelTwoId;
    }

    public String getSubjectLevelTwo() {
        return subjectLevelTwo;
    }

    public void setSubjectLevelTwo(String subjectLevelTwo) {
        this.subjectLevelTwo = subjectLevelTwo;
    }

    public int getTotalLikeCount() {
        return totalLikeCount;
    }

    public void setTotalLikeCount(Integer totalLikeCount) {
        this.totalLikeCount = totalLikeCount;
    }
}
