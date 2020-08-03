package com.wind.clerk.oauth.dao.mapper;



import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;

public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getById(Integer id);
}
