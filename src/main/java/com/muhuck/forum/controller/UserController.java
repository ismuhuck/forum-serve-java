package com.muhuck.forum.controller;

import com.muhuck.forum.entity.User;
import com.muhuck.forum.mapper.UserMapper;
import com.muhuck.forum.model.Result;
import com.muhuck.forum.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户登录API
 * 注解@RequestMapping设定了这个Controller类所有接口的前置路径/api/user，该前置路径会和下面每一个接口的路径拼接
 * 例如下面登录接口标注的是@GetMapping("/login")，那么登录接口的实际路径是：/api/user/login
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * session的字段名
     */
    public static final String SESSION_NAME = "userInfo";

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors) {
        if (errors.hasErrors()) {
            return  Result.resultFailed(errors.getFieldError().getDefaultMessage());

        } else {
//            调用注册服务
            return  userService.register(user);
        }
    }
    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        // 如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            return Result.resultFailed(errors.getFieldError().getDefaultMessage());
        }
        // 调用登录服务
        Result<User> result = userService.login(user);
        // 如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/is-login")
    public Result<User> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return userService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param user    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PatchMapping("/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        User sessionUser = (User) session.getAttribute(SESSION_NAME);
        if (sessionUser.getUserid() != user.getUserid()) {
            return Result.resultFailed("当前登录用户和被修改用户不一致，终止！");
        }
        Result<User> result = userService.update(user);
        // 修改成功则刷新session信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        return Result.resultSuccess("用户退出登录成功！");
    }

//    @GetMapping("/user/{id}")
//    public String getUserById(@PathVariable int id) {
//        return "根据Id获取用户";
//    }
}
