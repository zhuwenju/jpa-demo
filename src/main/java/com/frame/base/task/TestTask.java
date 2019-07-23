
package com.frame.base.task;

import com.frame.base.entity.OrderInfo;
import com.frame.base.service.IOrderService;
import com.frame.base.common.Constants;
import com.frame.base.common.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 测试定时任务(fixedRate在项目启动时会启动，cron不会启动)
 */
@ Component // 此注解必加
@ EnableScheduling // 此注解必加
public class TestTask
{

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    @ Autowired
    private IOrderService orderService;

    @ Scheduled(fixedRate = 10 * 60 * 1000) // 每10分钟执行一次
    public void task()
    {
        logger.info("测试定时任务!");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, -15);
        List<OrderInfo> orderInfoList = orderService.findByCreateTimeLessThan(calendar.getTime());

        if (!CollectionUtils.isEmpty(orderInfoList))
        {
            for (OrderInfo o : orderInfoList)
            {
                o.setStatus(Constants.STATUS.OVER_TIME.getCode());
                o.setUpdateTime(new Date());
                orderService.update(o);
                logger.info(o.getOrderNo() + "号订单超时");
            }
        }
        System.out.println(orderInfoList);
    }

    //@Scheduled(cron = "0/100 * * * * ?") // 每2秒执行一次
    public void doSomething() throws Exception
    {
        logger.info("每2秒执行一个的定时任务：" + new Date());
    }

}
