package com.frame.base.dao;

import com.frame.base.dao.support.IBaseDao;
import com.frame.base.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);

}
