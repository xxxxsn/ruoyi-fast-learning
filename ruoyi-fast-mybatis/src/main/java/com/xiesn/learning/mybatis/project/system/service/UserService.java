package com.xiesn.learning.mybatis.project.system.service;
import java.util.List;

import com.xiesn.learning.mybatis.project.system.domain.User;
public interface UserService{


    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);



	List<User> listByAll(User user);


}
