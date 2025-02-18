package com.muhuck.forum.service;

import com.muhuck.forum.entity.User;
import com.muhuck.forum.model.Result;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    /**
     * 用户注册
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> register(User user);

    /**
     * 用户登录
     * @param user 用户对象
     * @return 登录结果
     */
    Result<User> login(User user);

    /**
     * 修改用户信息
     * @return 修改结果
     */
    Result<User> update(User user) throws Exception;

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并对比）
     * @param session 请求session
     * @return 返回结果，若用户已登录则返回用户信息
     */
    Result<User> isLogin(HttpSession session);
}
