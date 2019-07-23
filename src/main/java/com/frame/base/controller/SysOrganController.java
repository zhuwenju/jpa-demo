/**
 * @filename:SysOrganController 2019年7月11日
 * @project demo-manage  V1.0
 * Copyright(c) 2018 admin Co. Ltd.
 * All right reserved.
 */

package com.frame.base.controller;

import org.apache.commons.lang3.StringUtils;
import com.frame.base.service.specification.SpecificationOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frame.base.common.Result;
import com.frame.base.entity.SysOrgan;
import com.frame.base.service.ISysOrganService;
import com.frame.base.service.specification.SimpleSpecificationBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @Description:  组织机构接口层
 * @Author:       admin
 * @CreateDate:   2019年7月11日
 * @Version:      V1.0
 *
 */
@ Api(value = "组织机构")
@ RestController
@ RequestMapping("/sysOrgan")
public class SysOrganController extends BaseController
{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ Autowired
    public ISysOrganService sysOrganServiceImpl;

    /**
     * @explain 查询组织机构对象  <swagger GET请求>
     * @param   id
     * @return  sysOrgan
     * @author  admin
     * @time    2019年7月11日
     */
    @ GetMapping("/info/{id}")
    @ ApiOperation(value = "获取组织机构信息", notes = "获取组织机构信息[sysOrgan]，作者：admin")
    @ ApiImplicitParam(paramType = "path", name = "id", value = "组织机构id", required = true, dataType = "String")
    public Result info(@ PathVariable("id") String id)
    {
        SysOrgan sysOrgan = null;
        try
        {
            sysOrgan = sysOrganServiceImpl.find(id);
        }
        catch(Exception e)
        {
            logger.error("获取组织机构执行异常：" + e.getMessage());
            return Result.error("执行异常，请稍后重试");
        }
        return Result.ok().put("data", sysOrgan);
    }

    /**
     * @explain 添加组织机构对象
     * @param   sysOrgan
     * @return  Result
     * @author  admin
     * @time    2019年7月11日
     */
    @ PostMapping("/insert")
    @ ApiOperation(value = "添加组织机构", notes = "添加组织机构[sysOrgan],作者：admin")
    public Result insert(SysOrgan sysOrgan)
    {
        try
        {
            sysOrganServiceImpl.save(sysOrgan);

        }
        catch(Exception e)
        {
            logger.error("添加组织机构执行异常：" + e.getMessage());
            return Result.error("执行异常，请稍后重试");
        }
        return Result.ok().put("data", sysOrgan);
    }

    /**
     * @explain 删除组织机构对象
     * @param   id
     * @return  Result
     * @author  admin
     * @time    2019年7月11日
     */
    @ PostMapping("/delete")
    @ ApiOperation(value = "删除组织机构", notes = "删除组织机构,作者：admin")
    @ ApiImplicitParam(paramType = "query", name = "id", value = "组织机构id", required = true, dataType = "String")
    public Result delete(String id)
    {
        try
        {
            sysOrganServiceImpl.delete(id);
        }
        catch(Exception e)
        {
            logger.error("删除组织机构执行异常：" + e.getMessage());
            return Result.error("执行异常，请稍后重试");
        }
        return Result.ok("删除成功");
    }

    /**
     * @explain 修改组织机构对象
     * @param   sysOrgan
     * @return  sysOrgan
     * @author  admin
     * @time    2019年7月11日
     */
    @ ApiOperation(value = "修改组织机构", notes = "修改组织机构[sysOrgan],作者：admin")
    @ PostMapping("/update")
    public Result update(SysOrgan sysOrgan)
    {
        try
        {
            sysOrganServiceImpl.update(sysOrgan);
        }
        catch(Exception e)
        {
            logger.error("修改组织机构执行异常：" + e.getMessage());
            return Result.error("执行异常，请稍后重试");
        }
        return Result.ok("修改成功");
    }

    /**
     * @explain 分页条件查询组织机构
     * @return  Result
     * @author  admin
     * @time    2019年7月11日
     */
    @ GetMapping("/getPageSysOrgan")
    @ ApiOperation(value = "分页查询", notes = "分页查询返回对象[PageInfo<SysOrgan>],作者：admin")
    @ ApiImplicitParams({
        @ ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ ApiImplicitParam(paramType = "query", name = "pageSize", value = "页行数", required = true, dataType = "int")})
    public Result getSysOrganBySearch()
    {

        SimpleSpecificationBuilder<SysOrgan> builder = new SimpleSpecificationBuilder<SysOrgan>();
        String searchText = request.getParameter("searchText");
        if (StringUtils.isNotBlank(searchText))
        {
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
        Page<SysOrgan> page = sysOrganServiceImpl.findAll(builder.generateSpecification(),
            getPageRequest());

        return Result.ok().put("data", page);
    }
}