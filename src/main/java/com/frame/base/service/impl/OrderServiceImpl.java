
package com.frame.base.service.impl;

import com.frame.base.dao.IOrderDao;
import com.frame.base.dao.support.IBaseDao;
import com.frame.base.entity.OrderInfo;
import com.frame.base.service.IOrderService;
import com.frame.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@ Service
public class OrderServiceImpl extends BaseServiceImpl<OrderInfo, Integer> implements IOrderService
{

    @ Autowired
    private IOrderDao orderDao;

    @ Override
    public IBaseDao<OrderInfo, Integer> getBaseDao()
    {
        return this.orderDao;
    }

    @ Override
    public List<OrderInfo> findByCreateTimeLessThan(Date date)
    {
        return orderDao.findByCreateTimeLessThan(date);
    }
}
