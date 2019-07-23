package com.frame.base.dao;

import com.frame.base.dao.support.IBaseDao;
import com.frame.base.entity.Role;

import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

}
