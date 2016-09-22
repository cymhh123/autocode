package org.chyou.autocode.modules.datasource.controller;

import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;
import org.chyou.autocode.modules.datasource.service.DataSourceConfigService;
import org.chyou.autocode.modules.datasource.utils.DBConnect;
import org.durcframework.util.JsonUtil;
import org.durcframework.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.chyou.autocode.modules.datasource.entity.TableBean;
import org.chyou.autocode.modules.datasource.selector.SQLService;
import org.chyou.autocode.modules.datasource.selector.SQLServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表Controller
 */
@Controller
public class TableController {
	
	@Autowired
	private DataSourceConfigService dataSourceConfigService;

	/**
	 * 查询表列表
	 * @param dcId
	 * @return
	 */
	@RequestMapping("listTable.do")
	public ModelAndView listTable(int dcId){
		//查询数据源
		DataSourceConfig dataSourceConfig = dataSourceConfigService.get(dcId);
		//测试链接是否成功
		String resultMsg = DBConnect.testConnection(dataSourceConfig);
		
		if(StringUtils.hasText(resultMsg)){
			return ResultUtil.error(resultMsg);
		}
		//获得对应SQL服务
		SQLService service = SQLServiceFactory.build(dataSourceConfig);
		//获取表信息
		List<TableBean> list = service.getTableSelector(dataSourceConfig).getTableList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows", list);
		
		return ResultUtil.buildModelAndView(JsonUtil.toJsonString(map));
	}
	
}
