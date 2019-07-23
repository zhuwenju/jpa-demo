package com.frame.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.frame.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Entity
@Table(name = "tb_order")
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 主订单号
     */
    private String mainOrderNo;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 购买方式
     */
    private String buyType;

    /**
     * 意向金
     */
    private BigDecimal earnestAmount;

    /**
     * 经销商城市Id
     */
    private Integer dealerCityId;

    /**
     * 经销商Id
     */
    private Integer dealerId;

    /**
     * 经销商名称
     */
    private String dealerName;

    /**
     * 商品ID
     */
    private Integer goodsId;


    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 是否是丰田老车主
     */
    private String isOldCarowner;

    /**
     * 提车人手机号
     */
    private String extractMobilephone;

    /**
     * 提车人姓名
     */
    private String extractName;

    /**
     * 提车人身份证号
     */
    private String idCardNo;

    /**
     * 订单来源渠道
     */
    private String orderChannel;

    /**
     * 订单来源终端
     */
    private String orderTerminal;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     *
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMainOrderNo() {
        return mainOrderNo;
    }

    public void setMainOrderNo(String mainOrderNo) {
        this.mainOrderNo = mainOrderNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public BigDecimal getEarnestAmount() {
        return earnestAmount;
    }

    public void setEarnestAmount(BigDecimal earnestAmount) {
        this.earnestAmount = earnestAmount;
    }

    public Integer getDealerCityId() {
        return dealerCityId;
    }

    public void setDealerCityId(Integer dealerCityId) {
        this.dealerCityId = dealerCityId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIsOldCarowner() {
        return isOldCarowner;
    }

    public void setIsOldCarowner(String isOldCarowner) {
        this.isOldCarowner = isOldCarowner;
    }

    public String getExtractMobilephone() {
        return extractMobilephone;
    }

    public void setExtractMobilephone(String extractMobilephone) {
        this.extractMobilephone = extractMobilephone;
    }

    public String getExtractName() {
        return extractName;
    }

    public void setExtractName(String extractName) {
        this.extractName = extractName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public String getOrderTerminal() {
        return orderTerminal;
    }

    public void setOrderTerminal(String orderTerminal) {
        this.orderTerminal = orderTerminal;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
