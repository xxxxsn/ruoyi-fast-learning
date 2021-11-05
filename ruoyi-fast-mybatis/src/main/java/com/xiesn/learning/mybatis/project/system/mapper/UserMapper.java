package com.xiesn.learning.mybatis.project.system.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.xiesn.learning.mybatis.project.system.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    List<User> listByAll(User user);


}