package com.muhuck.forum.controller;

import com.muhuck.forum.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
//    RequestMapping 不指定method时，任意请求方法都可以，当指定method时 可用GetMapping代替
//    @GetMapping("/hello")
//    http://localhost:4065/hello?nickname=muhuck&age=19
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String nickname, @RequestParam(value = "age", required = false) String a) {
//        @RequestParam将请求参数绑定到控制器的方法参数上，接收的参数来自HTTP请求体或请求url的QueryString，当
//        请求的参数名称与Controller的业务方法参数名称一致时@RequestParam可以省略,相当于做了一个映射, required默认为true
        return "hello " + nickname + a;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user) {
        return "post请求1111";
    }

    @RequestMapping(value = "/register2", method = RequestMethod.POST)
    public String register2(@RequestBody User user) {
//        接收json数据时需要增加 @RequestBody注解
        System.out.println(user);
        return "user111";
    }
}
