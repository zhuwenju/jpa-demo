/**
 * @filename:${entityName}Controller ${createTime}
 * @project ${project}  ${version}
 * Copyright(c) 2018 ${author} Co. Ltd.
 * All right reserved.
 */
package ${controllerUrl};

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
import ${entityUrl}.${entityName};
import ${serviceUrl}.I${entityName}Service;
import com.frame.base.service.specification.SimpleSpecificationBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @Description:  ${entityComment}接口层
 * @Author:       ${author}
 * @CreateDate:   ${createTime}
 * @Version:      ${version}
 *
 */
@Api(value="${entityComment}")
@RestController
@RequestMapping("/${objectName}")
public class ${entityName}Controller extends BaseController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public I${entityName}Service ${objectName}ServiceImpl;

	/**
	 * @explain 查询${entityComment}对象  <swagger GET请求>
	 * @param   id
	 * @return  ${objectName}
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@GetMapping("/info/{id}")
	@ApiOperation(value = "获取${entityComment}信息", notes = "获取${entityComment}信息[${objectName}]，作者：${author}")
	@ApiImplicitParam(paramType="path", name = "id", value = "${entityComment}id", required = true, dataType = "${idType}")
	public Result info(@PathVariable("id")${idType} id){
		${entityName} ${objectName}=null;
		try {
			${objectName}=${objectName}ServiceImpl.find(id);
		} catch (Exception e) {
			logger.error("获取${entityComment}执行异常："+e.getMessage());
			return Result.error("执行异常，请稍后重试");
		}
		return Result.ok().put("data", ${objectName});
	}

	/**
	 * @explain 添加${entityComment}对象
	 * @param   ${objectName}
	 * @return  Result
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@PostMapping("/insert")
	@ApiOperation(value = "添加${entityComment}", notes = "添加${entityComment}[${objectName}],作者：${author}")
	public Result insert(${entityName} ${objectName}){
		try {
			${objectName}ServiceImpl.save(${objectName});

		} catch (Exception e) {
			logger.error("添加${entityComment}执行异常："+e.getMessage());
			return Result.error("执行异常，请稍后重试");
		}
		return Result.ok().put("data", ${objectName});
	}

	/**
	 * @explain 删除${entityComment}对象
	 * @param   id
	 * @return  Result
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除${entityComment}", notes = "删除${entityComment},作者：${author}")
	@ApiImplicitParam(paramType="query", name = "id", value = "${entityComment}id", required = true, dataType = "${idType}")
	public Result delete(${idType} id){
		try {
			${objectName}ServiceImpl.delete(id);
		} catch (Exception e) {
			logger.error("删除${entityComment}执行异常："+e.getMessage());
			return Result.error("执行异常，请稍后重试");
		}
		return Result.ok("删除成功");
	}

	/**
	 * @explain 修改${entityComment}对象
	 * @param   ${objectName}
	 * @return  ${objectName}
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@ApiOperation(value = "修改${entityComment}", notes = "修改${entityComment}[${objectName}],作者：${author}")
	@PostMapping("/update")
	public Result update(${entityName} ${objectName}){
		try {
			${objectName}ServiceImpl.update(${objectName});
		} catch (Exception e) {
			logger.error("修改${entityComment}执行异常："+e.getMessage());
            return Result.error("执行异常，请稍后重试");
		}
		return Result.ok("修改成功");
	}

	/**
	 * @explain 分页条件查询${entityComment}
	 * @return  Result
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@GetMapping("/getPage${entityName}")
	@ApiOperation(value = "分页查询", notes = "分页查询返回对象[PageInfo<${entityName}>],作者：${author}")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public Result get${entityName}BySearch(){

		SimpleSpecificationBuilder<${entityName}> builder = new SimpleSpecificationBuilder<${entityName}>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
		}
		Page<${entityName}> page = ${objectName}ServiceImpl.findAll(builder.generateSpecification(),getPageRequest());

		return Result.ok().put("data", page);
	}
}