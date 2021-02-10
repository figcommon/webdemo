package com.example.webdemo.controller;

import com.example.webdemo.mapper.UserMapper;
import com.example.webdemo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller //注解，自动将相应的资源加载，页面
public class hiController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/register")  //映射内容到localhost下hi路径
    public String reg(){
        return "register";
    }
    @RequestMapping("/register")  //映射到下面的方法
    public String register(HttpServletRequest request,Map<String,Object> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        User user1=userMapper.getuser(username);
        if (user1!=null){
            map.put("msg1","the user hae been registered!,pls register again");
            return "register";
        }else{
            userMapper.adduser(user);
            return "login";//不存在重名用户注册成功，跳转到登录页面
        }

        //return "register";//thymeleaf框架自动拼接。html
    }

    @RequestMapping("/getuser")
    public String getuser(HttpServletRequest request, Map<String,Object> map){
        String username=request.getParameter("username");
        User user=userMapper.getuser(username);
        if (user!=null){
            map.put("msg","the user hae been registered!");
            return "register";
        }else{
            map.put("msg","the use hasn't been used");
            return "register";
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,Map<String,Object> map){//前端请求信息
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User loginuser=userMapper.login(username,password);
        System.out.println(loginuser);
        map.put("msg2","the user "+loginuser+" login");
        return "login";
    }
}
