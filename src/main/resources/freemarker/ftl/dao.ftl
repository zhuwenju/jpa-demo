/**
* @filename:${entityName}Dao ${createTime}
* @project ${project}  ${version}
* Copyright(c) 2018 ${author} Co. Ltd.
* All right reserved.
*/
package ${daoUrl};

import com.frame.base.dao.support.IBaseDao;
import ${entityUrl}.${entityName};
import com.frame.base.service.support.IBaseService;
import org.springframework.stereotype.Repository;

/**
*
* @Description:  ${entityComment}——DAO
* @Author:       ${author}
* @CreateDate:   ${createTime}
* @Version:      ${version}
*
*/
@Repository
public interface ${entityName}Dao extends IBaseDao<${entityName}, ${idType}> {


}
	