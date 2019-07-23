package com.frame.base.dao;

import com.frame.base.entity.OrderInfo;
import com.frame.base.dao.support.IBaseDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderDao extends IBaseDao<OrderInfo, Integer> {

    List<OrderInfo> findByCreateTimeLessThan(Date date);
}
