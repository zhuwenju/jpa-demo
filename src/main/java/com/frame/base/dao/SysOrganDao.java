/**
* @filename:SysOrganDao 2019年7月11日
* @project demo-manage  V1.0
* Copyright(c) 2018 admin Co. Ltd.
* All right reserved.
*/
package com.frame.base.dao;

import com.frame.base.dao.support.IBaseDao;
import com.frame.base.entity.SysOrgan;
import com.frame.base.service.support.IBaseService;
import org.springframework.stereotype.Repository;

/**
*
* @Description:  组织机构——DAO
* @Author:       admin
* @CreateDate:   2019年7月11日
* @Version:      V1.0
*
*/
@Repository
public interface SysOrganDao extends IBaseDao<SysOrgan, String> {


}
