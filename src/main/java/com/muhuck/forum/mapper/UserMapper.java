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
    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增成功记录条数
     */
    int add(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改成功记录条数
     */
    int update(User user);

    /**
     * 根据id获取用户
     *
     * @param userid 用户id
     * @return 用户对象
     */
    User getById(Integer userid);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getByUsername(String username);
}
