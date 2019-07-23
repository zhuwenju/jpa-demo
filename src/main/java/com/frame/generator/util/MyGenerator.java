/**
 * @filename:UserController 2019年4月9日
 * @project wallet-manage  V1.0
 * Copyright(c) 2018 BianPeng Co. Ltd.
 * All right reserved.
 */

package com.frame.generator.util;

import java.sql.SQLException;
import java.util.Date;

import com.frame.generator.entity.BasisInfo;

/**
 * Copyright: Copyright (c) 2019
 *
 * <p>
 * 说明： 自动生成工具
 * </P>
 * <p>
 * 源码地址：https://gitee.com/flying-cattle/mybatis-dsc-generator
 * </P>
 */
public class MyGenerator
{
    // 基础信息：项目名、作者、版本
    public static final String PROJECT = "demo-manage";
    public static final String AUTHOR = "admin";
    public static final String VERSION = "V1.0";
    // 数据库连接信息：连接URL、用户名、秘密、数据库名
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/frame?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=true&serverTimezone=UTC";
    public static final String NAME = "root";
    public static final String PASS = "123456";
    public static final String DATABASE = "frame";
    // 类信息：类名、对象名（一般是【类名】的首字母小些）、类说明、时间
    public static final String TABLE = "sys_organ";
    public static final String CLASSCOMMENT = "组织机构";
    public static final String TIME = "2019年7月11日";
    public static final String AGILE = new Date().getTime() + "";
    // 路径信息，分开路径方便聚合工程项目，微服务项目
    public static final String DAO_URL = "com.frame.base.dao";
    public static final String ENTITY_URL = "com.frame.base.entity";
    public static final String SERVICE_URL = "com.frame.base.service";
    public static final String SERVICE_IMPL_URL = "com.frame.base.service.impl";
    public static final String CONTROLLER_URL = "com.frame.base.controller";
    public static final String serviceLocation = "G:\\zhuwenju\\javawork\\02-A-demo-jpa\\src\\main\\java\\";
    public static final String controllerLocation = "G:\\zhuwenju\\javawork\\02-A-demo-jpa\\src\\main\\java\\";

    public static void main(String[] args)
    {
        BasisInfo bi = new BasisInfo(PROJECT, AUTHOR, VERSION, URL, NAME, PASS, DATABASE, TIME,
            AGILE, ENTITY_URL, DAO_URL, SERVICE_URL, SERVICE_IMPL_URL, CONTROLLER_URL);
        bi.setTable(TABLE);
        bi.setEntityName(MySqlToJavaUtil.getClassName(TABLE));
        bi.setObjectName(MySqlToJavaUtil.changeToJavaFiled(TABLE));
        bi.setEntityComment(CLASSCOMMENT);
        try
        {
            bi = EntityInfoUtil.getInfo(bi);
            //			String aa1 = Generator.createEntity(fileUrl, bi).toString();

            String aa2 = Generator.createDao(serviceLocation, bi).toString();
            String aa4 = Generator.createService(serviceLocation, bi).toString();
            String aa5 = Generator.createServiceImpl(serviceLocation, bi).toString();
            String aa6 = Generator.createController(controllerLocation, bi).toString();

            // 是否创建swagger配置文件
            // String aa7 = Generator.createSwaggerConfig(fileUrl, bi).toString();
            //
            System.out.println(aa2);
            System.out.println(aa4);
            System.out.println(aa5);
            System.out.println(aa6);

            // System.out.println(aa7);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
