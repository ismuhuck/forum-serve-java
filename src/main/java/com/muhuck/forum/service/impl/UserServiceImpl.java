package com.muhuck.forum.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.muhuck.forum.controller.UserController;
import com.muhuck.forum.entity.User;
import com.muhuck.forum.mapper.UserMapper;
import com.muhuck.forum.model.Result;
import com.muhuck.forum.service.UserService;
import com.muhuck.forum.util.ClassExamine;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<User> register (User user) {
        Result<User> result = new Result<>();
        User getUser = userMapper.getByUsername(user.getUsername());
        if(getUser != null) {
            return Result.resultFailed("该用户名已存在");
        }
//        加密存储用户的密码
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        userMapper.add(user);
        return Result.resultSuccess("用户注册成功", user);
    }

    @Override
    public Result<User> login(User user) {
        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getByUsername(user.getUsername());
        if (getUser == null) {
            return Result.resultFailed("用户不存在！");
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtil.md5Hex(user.getPassword()))) {
            return Result.resultFailed("用户名或者密码错误！");
        }
        // 设定登录成功消息
        return Result.resultSuccess("登录成功！", getUser);
    }

    @Override
    public Result<User> update(User user) throws Exception {
        // 去数据库查找用户
        User getUser = userMapper.getById(user.getUserid());
        if (getUser == null) {
            return Result.resultFailed("用户不存在！");
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StrUtil.isEmpty(user.getPassword())) {
            // 加密储存
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        }
        // 对象互补
        ClassExamine.objectOverlap(user, getUser);
        // 存入数据库
        userMapper.update(user);
        return Result.resultSuccess("修改用户成功！", user);
    }

    @Override
    public Result<User> isLogin(HttpSession session) {
        // 从session中取出用户信息
        User sessionUser = (User) session.getAttribute(UserController.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionUser == null) {
            return Result.resultFailed("用户未登录！");
        }
        // 登录了则去数据库取出信息进行比对
        User getUser = userMapper.getById(sessionUser.getUserid());
        // 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            return Result.resultFailed("用户信息无效！");
        }
        return Result.resultSuccess("用户已登录！", getUser);
    }
}
