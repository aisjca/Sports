package com.jc.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.comment.entity.Comment;
import com.jc.comment.entity.vo.CommentVo;
import com.jc.comment.mapper.CommentMapper;
import com.jc.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-11-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Map<String, Object> getCommentFrontList(String courseId, Page<Comment> pageComment) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageComment, wrapper);
        Map<String, Object> map = new HashMap<>();
        List<Comment> records = pageComment.getRecords();
        long current = pageComment.getCurrent();
        long pages = pageComment.getPages();
        long size = pageComment.getSize();
        long total = pageComment.getTotal();
        boolean hasNext = pageComment.hasNext();//下一页
        boolean hasPrevious = pageComment.hasPrevious();//上一页
        System.out.println(total);
        //把分页数据获取出来，放到map集合
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public boolean addComment(CommentVo commentVo) {
        Comment comment = new Comment();
        comment.setAvatar(commentVo.getAvatar());
        comment.setContent(commentVo.getContent());
        comment.setCourseId(commentVo.getCourseId());
        comment.setMemberId(commentVo.getMemberId());
        comment.setNickname(commentVo.getNickname());
        comment.setTeacherId(commentVo.getTeacherId());
        int row = baseMapper.insert(comment);
        return row == 0 ? false : true;
    }
}
