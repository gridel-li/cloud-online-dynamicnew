package com.sgcc.zj.manager.controller.generator;

import com.alibaba.fastjson.JSON;
import com.sgcc.zj.common.base.PageUtils;
import com.sgcc.zj.common.base.Query;
import com.sgcc.zj.common.base.R;
import com.sgcc.zj.common.xss.XssHttpServletRequestWrapper;
import com.sgcc.zj.service.generator.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 */
@RestController
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Resource
	private SysGeneratorService sysGeneratorService;

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		int total = sysGeneratorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] tableNames = new String[]{};
		//获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		//转义特殊字符
		String tables = java.net.URLDecoder.decode(orgRequest.getParameter("tables"),"UTF-8");
		tableNames = JSON.parseArray(tables).toArray(tableNames);

		byte[] data = sysGeneratorService.generatorCode(tableNames);

		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"file.zip\"");
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
}
