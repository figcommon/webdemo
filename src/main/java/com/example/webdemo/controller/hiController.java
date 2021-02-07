package com.example.webdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //注解，自动将相应的资源加载
public class hiController {
    @GetMapping("/hi")  //映射内容
    @RequestMapping("/hi")  //映射到下面的方法
    public String hi() {
        return "hello Spring Boot";
    }

}
