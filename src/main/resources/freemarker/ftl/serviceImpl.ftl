/**
* @filename:${entityName}ServiceImpl ${createTime}
* @project ${project}  ${version}
* Copyright(c) 2018 ${author} Co. Ltd.
* All right reserved.
*/
package ${serviceImplUrl};

import com.frame.base.dao.support.IBaseDao;
import com.frame.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${entityUrl}.${entityName};
import ${daoUrl}.${entityName}Dao;
import ${serviceUrl}.I${entityName}Service;

/**
*
* @Description:  ${entityComment}——SERVICEIMPL
* @Author:       ${author}
* @CreateDate:   ${createTime}
* @Version:      ${version}
*
*/
@Service
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}, ${idType}> implements I${entityName}Service {

    @Autowired
    public ${entityName}Dao ${objectName}Dao;

    @ Override
    public IBaseDao<${entityName}, ${idType}> getBaseDao()
    {
        return this.${objectName}Dao;
    }
}