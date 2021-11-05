package com.xiesn.learning.mybatis.project.system.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiesn.learning.mybatis.project.system.domain.User;
import com.xiesn.learning.mybatis.project.system.mapper.UserMapper;
import com.xiesn.learning.mybatis.project.system.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

	@Override
	public List<User> listByAll(User user){
		 return userMapper.listByAll(user);
	}




}
