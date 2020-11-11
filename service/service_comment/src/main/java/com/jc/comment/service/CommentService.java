package com.jc.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.comment.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.comment.entity.vo.CommentVo;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author jc
 * @since 2020-11-08
 */
public interface CommentService extends IService<Comment> {

    Map<String, Object> getCommentFrontList(String courseId, Page<Comment> pageComment);

    boolean addComment(CommentVo commentVo);
}
