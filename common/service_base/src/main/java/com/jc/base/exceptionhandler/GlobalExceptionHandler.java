package com.jc.base.exceptionhandler;

import com.jc.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/04 20:57
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常
    @ExceptionHandler
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("执行ArithmeticException异常");
    }

    //自定义异常
    @ExceptionHandler(SportException.class)
    @ResponseBody
    public Result error(SportException e) {
        log.error(e.getMessage());//写入log日志信息
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}
