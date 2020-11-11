package com.jc.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.base.exceptionhandler.SportException;
import com.jc.edu.entity.Chapter;
import com.jc.edu.entity.Video;
import com.jc.edu.entity.chapter.ChapterVo;
import com.jc.edu.entity.chapter.VideoVo;
import com.jc.edu.mapper.ChapterMapper;
import com.jc.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-09-15
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    //课程大纲列表，根据课程id进行查询
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //创建list集合，用于最终封装数据
        List<ChapterVo>  finalList = new ArrayList<>();
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Video> videoList = videoService.list(wrapperVideo);
        //3 遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for (int i = 0; i < chapterList.size(); i++) {
            //每个章节
            Chapter chapter = chapterList.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //创建集合，用于封装章节的小节
            List<VideoVo> childrenVideo = new ArrayList<>();
            //4 遍历查询小节list集合，进行封装
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(j);
                if (video.getChapterId().equals(chapterVo.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    //放到小节封装集合
                    childrenVideo.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(childrenVideo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

        }
        return  finalList;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) {
            throw new SportException(20001, "要把小节清空，才能删除");
        } else {
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    //2 根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

}
