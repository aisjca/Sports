package com.jc.base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/04 21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportException extends RuntimeException {

    private Integer code;//状态码

    private String msg;//异常信息
}
