package com.frame.base.service;

import com.frame.base.entity.OrderInfo;
import com.frame.base.service.support.IBaseService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IOrderService extends IBaseService<OrderInfo, Integer> {

    List<OrderInfo> findByCreateTimeLessThan(Date date);
}
