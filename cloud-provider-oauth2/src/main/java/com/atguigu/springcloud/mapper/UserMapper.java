package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select( "select id , username , password from user where username = #{username}" )
    User loadUserByUsername(@Param("username") String username);
}
