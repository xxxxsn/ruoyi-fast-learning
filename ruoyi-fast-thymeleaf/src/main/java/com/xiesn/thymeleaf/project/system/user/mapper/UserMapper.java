package com.xiesn.thymeleaf.project.system.user.mapper;


import com.xiesn.thymeleaf.project.system.user.domain.User;

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