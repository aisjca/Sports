package com.jc.edu.controller;

import com.jc.edu.entity.Chapter;
import com.jc.edu.entity.chapter.ChapterVo;
import com.jc.edu.service.ChapterService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //课程大纲列表,根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("allChapterVideo", list);
    }

    //添加章节
    @PostMapping("addChapter")
    public Result addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        return Result.ok().data("chapter", chapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        return flag == true ? Result.ok() : Result.error();
    }
}

