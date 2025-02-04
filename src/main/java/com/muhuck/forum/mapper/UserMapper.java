package com.muhuck.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muhuck.forum.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper //mybatis 用法
//public interface UserMapper {
////      查询所有用户
//    @Select("select * from user")
//    public List<User> find();
//    @Insert("insert into user values(#{userid},#{username},#{email},#{birthday}, #{nickname},#{age})")
//    public int insert(User user);
//}
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
