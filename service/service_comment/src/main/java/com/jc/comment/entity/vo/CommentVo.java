package com.jc.comment.entity.vo;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/10 22:25
 */
public class CommentVo {
    private String memberId;
    private String avatar;
    private String nickname;
    private String courseId;
    private String content;
    private String teacherId;

    public String getMemberId() {
        return memberId;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getContent() {
        return content;
    }

    public String getTeacherId() {
        return teacherId;
    }
}
