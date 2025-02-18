package com.muhuck.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotEmpty;

public class User {
    @TableId(type = IdType.AUTO)
    private int userid;
//    @NotEmpty(message = "昵称不能为空！")
    private String nickname;
    private int age;
    @NotEmpty(message = "用户名不能为空！")
    private String username;
    private String birthday;
    private String email;
    private String password;
// command + N 快速生成 setter和getter
    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
