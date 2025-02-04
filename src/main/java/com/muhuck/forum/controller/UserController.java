package com.muhuck.forum.controller;

import com.muhuck.forum.entity.User;
import com.muhuck.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user-list")
    public List queryList() {
        List<User> list = userMapper.selectList(null);
        System.out.println("list"+ list);
        return list;
    }
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id) {
        return "根据Id获取用户";
    }

    @PostMapping("/user/register")
    public String userRegister(@RequestBody User user) {
        int i = userMapper.insert(user);
        if(i>0) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }
}
