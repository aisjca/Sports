package com.jc.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/13 10:32
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String filename = "/Users/jiangcheng/IdeaProjects/sport_parent/service/service_edu/src/test/java/com/jc/eduservice/excel/text.xlsx";
//        //2 调用easyexcel里面的方法实现写操作
//        //write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());

        //实现excel读操作
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
