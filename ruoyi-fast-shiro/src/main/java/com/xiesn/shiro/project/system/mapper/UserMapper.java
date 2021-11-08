package com.xiesn.shiro.project.system.mapper;


import com.xiesn.shiro.project.system.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    List<User> listByAll(User user);


}