package com.jc.comment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.comment.entity.Comment;
import com.jc.comment.entity.vo.CommentVo;
import com.jc.comment.service.CommentService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-11-08
 */
@RestController
@RequestMapping("/educomment/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    //分页查找评论信息
    @GetMapping("getCommentList/{courseId}/{page}/{limit}")
    public Result getCommentList(@PathVariable String courseId, @PathVariable long page, @PathVariable long limit) {
        Page<Comment> pageComment = new Page<>(page, limit);
        Map<String, Object> map = commentService.getCommentFrontList(courseId, pageComment);
        return Result.ok().data(map);
    }

    //添加评论
    @PostMapping("addComment")
    public Result addComment(@RequestBody CommentVo commentVo) {
        boolean flag = commentService.addComment(commentVo);
        return flag == true ? Result.ok() : Result.error();
    }

    //删除评论
    @DeleteMapping("deleteComment/{commentId}")
    public Result deleteComment(@PathVariable String commentId) {
        boolean flag=commentService.removeById(commentId);
        return flag==true?Result.ok():Result.error();
    }
}

