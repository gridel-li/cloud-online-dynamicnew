package com.sgcc.zj.manager.controller.generator;

import com.alibaba.fastjson.JSON;
import com.sgcc.zj.core.aop.annotation.PGControllerMonitor;
import com.sgcc.zj.core.base.PageUtils;
import com.sgcc.zj.core.base.Query;
import com.sgcc.zj.core.base.R;
import com.sgcc.zj.core.xss.XssHttpServletRequestWrapper;
import com.sgcc.zj.service.generator.SysGeneratorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
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
	@PGControllerMonitor
	@ResponseBody
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="获取数据库表", notes="")
	@ApiImplicitParam(name = "params" , paramType = "body",examples = @Example({
			@ExampleProperty(value = "tableName:test", mediaType = "form-data")
	}))
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		int total = sysGeneratorService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

		//System.out.println(HttpClientUtils.sendGet("http://127.0.0.1:8764/hello?name=ss"));

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 生成代码
	 */
	@PGControllerMonitor
	@RequestMapping(value = "/code",method = {RequestMethod.GET})
	@ApiOperation(value = "生成代码")
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




	static Connection conn = null;

	public static void initConn() throws ClassNotFoundException, SQLException {
		String sql;
		String url = "jdbc:mysql://111.231.110.198:3306/jmh_db?"
				+ "user=root&password=_Lyj112233&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";

		try {
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.cj.jdbc.Driver");// 动态加载mysql驱动

			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String randomStr(int size) {
		//定义一个空字符串
		String result []= {"一","二","三","四","五","溜","去","吧","就","是","说","双方都","身份证","安抚","存储"};
		String newresult = "";
		for (int i = 0; i < size; i++) {
			//生成一个97~122之间的int类型整数
			int intVal = (int) (Math.random()*14 + 1);
				//强制转换（char）intVal 将对应的数值转换为对应的字符
			//将字符进行拼接
			newresult = newresult + result[intVal];
		}
		//System.out.println(newresult);
		//输出字符串
		return newresult;
	}


	public static void insert() {
		// 开时时间
		Long begin = new Date().getTime();
		// sql前缀
		String prefix = "INSERT INTO cl_authitem_log(merchant_code, user_id, authitem_code, authitem_name, status, is_push, auth_times, begin_time, end_time, elapsed_time, begin_millis, valid_time, auth_result) VALUES ";
	try {
			// 保存sql后缀
			StringBuffer suffix = new StringBuffer();
			// 设置事务为非自动提交
			conn.setAutoCommit(false);
			// Statement st = conn.createStatement();
			// 比起st，pst会更好些
			PreparedStatement pst = conn.prepareStatement(" ");
			// 外层循环，总提交事务次数
			for (int i = 1; i <= 100; i++) {
				// 第次提交步长
				for (int j = 1; j <= 10000; j++) {
					// 构建sql后缀
					suffix.append("(" + "'lPQyte', '700B79BB95B14975FF7CEEC415B7195E', 'IDCARD', '身份证认证', 1, 1, 1, '2018-10-08 21:29:50', '2018-10-08 21:29:54', '0天0小时0分3秒699毫秒', 1539005390228, '2118-09-13 21:29:54'"+ ",'"+randomStr(14) + "'),");
				}
				// 构建完整sql
				String sql = prefix + suffix.substring(0, suffix.length() - 1);
				// 添加执行sql
				pst.addBatch(sql);
				// 执行操作
				pst.executeBatch();
				// 提交事务
				conn.commit();
				// 清空上一次添加的数据
				suffix = new StringBuffer();
			}
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 结束时间
		Long end = new Date().getTime();
		// 耗时
		System.out.println("cast : " + (end - begin) / 1000 + " ms");
	}


	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		//initConn();
		//insert();

	}





}
