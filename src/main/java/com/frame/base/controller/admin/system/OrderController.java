
package com.frame.base.controller.admin.system;

import com.alibaba.fastjson.JSONObject;
import com.frame.base.common.Constants;
import com.frame.base.common.JsonResult;
import com.frame.base.common.utils.CollectionUtils;
import com.frame.base.common.utils.HttpClientUtils;
import com.frame.base.controller.BaseController;
import com.frame.base.entity.OrderInfo;
import com.frame.base.service.IOrderService;
import com.frame.base.service.specification.SimpleSpecificationBuilder;
import com.frame.base.service.specification.SpecificationOperator.Operator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ Controller
@ RequestMapping("/admin/order")
public class OrderController extends BaseController
{

    @ Value("${mall.url}")
    private String mallUrl;

    @ Value("${mall.goodsId}")
    private String goodsId;

    @ Value("${mall.activityId}")
    private String activityId;

    @ Value("${mall.isOldCarowner}")
    private String isOldCarowner;

    @ Value("${mall.buyType}")
    private String buyType;

    @ Value("${mall.earnestAmount}")
    private String earnestAmount;

    @ RequestMapping("/index")
    public String index()
    {
        return "admin/order/index";
    }

    @ Autowired
    private IOrderService orderService;

    @ RequestMapping(value = {"/list"})
    @ ResponseBody
    public Page<OrderInfo> list()
    {
        SimpleSpecificationBuilder<OrderInfo> builder = new SimpleSpecificationBuilder<OrderInfo>();
        String searchText = request.getParameter("searchText");
        if (StringUtils.isNotBlank(searchText))
        {
            builder.add("orderNo", Operator.likeAll.name(), searchText);
        }
        Page<OrderInfo> page = orderService.findAll(builder.generateSpecification(),
            getPageRequest());
        return page;
    }

    @ RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    @ ResponseBody
    public JsonResult create(OrderInfo orderInfo)
    {
        try
        {
            orderInfo.setBuyType(this.buyType);
            orderInfo.setActivityId(this.activityId);
            orderInfo.setIsOldCarowner(this.isOldCarowner);
            orderInfo.setEarnestAmount(new BigDecimal(this.earnestAmount));
            orderInfo.setGoodsId(Integer.valueOf(this.goodsId));

            this.doPost(orderInfo);
            orderInfo.setStatus(Constants.STATUS.NO_PAY.getCode());
            orderInfo.setCreateTime(new Date());

            if (orderInfo.getOrderNo() == null)
            {
                return JsonResult.failure("下单失败");
            }

            orderService.save(orderInfo);

        }
        catch(Exception e)
        {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success(orderInfo);
    }

    @ RequestMapping(value = {"/payCallback"}, method = RequestMethod.POST)
    @ ResponseBody
    public String payCallback(String mainOrderNo, String orderNo, String orderAmount)
    {

        SimpleSpecificationBuilder<OrderInfo> builder = new SimpleSpecificationBuilder<OrderInfo>();
        builder.add("mainOrderNo", Operator.eq.name(), mainOrderNo);
        builder.addOr("orderNo", Operator.eq.name(), orderNo);

        List<OrderInfo> orderInfoList = orderService.findList(builder.generateSpecification(),
            new Sort(new Sort.Order("mainOrderNo")));

        if (!CollectionUtils.isEmpty(orderInfoList))
        {
            OrderInfo orderInfo = orderInfoList.get(0);
            orderInfo.setOrderAmount(new BigDecimal(orderAmount));
            orderInfo.setStatus(Constants.STATUS.PAY_SUCCESS.getCode());
            orderInfo.setUpdateTime(new Date());
            orderService.update(orderInfo);
        }

        return "success";
    }

    private JSONObject doPost(OrderInfo orderInfo)
    {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("dealerCityId", String.valueOf(orderInfo.getDealerCityId()));
        paramMap.put("dealerId", String.valueOf(orderInfo.getDealerId()));
        paramMap.put("dealerName", String.valueOf(orderInfo.getDealerName()));
        paramMap.put("goodsId", String.valueOf(orderInfo.getGoodsId()));
        paramMap.put("extractMobilephone", orderInfo.getExtractMobilephone());
        paramMap.put("extractName", orderInfo.getExtractName());
        paramMap.put("idCardNo", orderInfo.getIdCardNo());
        paramMap.put("orderChannel", orderInfo.getOrderChannel());

        paramMap.put("buyType", this.buyType);
        paramMap.put("activityId", this.activityId);
        paramMap.put("isOldCarowner", this.isOldCarowner);
        paramMap.put("earnestAmount", this.earnestAmount);
        paramMap.put("goodsId", this.goodsId);

        JSONObject jsonObject = HttpClientUtils.httpPost(this.mallUrl, paramMap);

        if (jsonObject != null)
        {
            JSONObject data = jsonObject.getJSONObject("data");

            if (data != null)
            {

                String orderNo = data.getString("orderNo");
                String mainOrderNo = data.getString("mainOrderNo");
                String goodsName = data.getString("goodsName");

                orderInfo.setOrderNo(orderNo);
                orderInfo.setMainOrderNo(mainOrderNo);
                orderInfo.setGoodsName(goodsName);

            }

        }

        return jsonObject;
    }

}
