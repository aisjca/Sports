package com.jc.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/18 00:14
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
