/**
* @filename:SysOrganServiceImpl 2019年7月11日
* @project demo-manage  V1.0
* Copyright(c) 2018 admin Co. Ltd.
* All right reserved.
*/
package com.frame.base.service.impl;

import com.frame.base.dao.support.IBaseDao;
import com.frame.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.base.entity.SysOrgan;
import com.frame.base.dao.SysOrganDao;
import com.frame.base.service.ISysOrganService;

/**
*
* @Description:  组织机构——SERVICEIMPL
* @Author:       admin
* @CreateDate:   2019年7月11日
* @Version:      V1.0
*
*/
@Service
public class SysOrganServiceImpl extends BaseServiceImpl<SysOrgan, String> implements ISysOrganService {

    @Autowired
    public SysOrganDao sysOrganDao;

    @ Override
    public IBaseDao<SysOrgan, String> getBaseDao()
    {
        return this.sysOrganDao;
    }
}