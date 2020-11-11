package com.jc.edu.service;

import com.jc.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
public interface ChapterService extends IService<Chapter> {
    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节的方法
    public boolean deleteChapter(String chapterId);

    // 根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
