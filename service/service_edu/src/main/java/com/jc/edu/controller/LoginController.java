package com.jc.edu.controller;

import com.jc.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/09/08 10:35
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LoginController {

    //login
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    //info
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
