package com.xidian.serviceedu.controller;

import com.xidian.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceedu/user")
@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin1").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
