package com.jc.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/13 10:29
 */
@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
