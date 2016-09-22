package org.chyou.autocode.modules.generator.controller;

import org.chyou.autocode.modules.datasource.service.DataSourceConfigService;
import org.durcframework.util.JsonUtil;
import org.durcframework.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;
import org.chyou.autocode.modules.generator.entity.CodeFile;
import org.chyou.autocode.modules.generator.entity.GeneratorParam;
import org.chyou.autocode.modules.generator.service.GeneratorService;

import java.util.List;

/**
 * 代码生成
 * @author hc.tang
 * 
 */
@Controller
public class GeneratorController {
	@Autowired
	private GeneratorService generatorService;
	@Autowired
	private DataSourceConfigService dataSourceConfigService;

	/**
	 * 生成代码
	 * @param generatorParam
	 * @return
	 */
	@RequestMapping("/generatFile.do")
	public ModelAndView generatFile(GeneratorParam generatorParam) {
		//获取数据源信息
		DataSourceConfig dataSourceConfig =
				dataSourceConfigService.get(generatorParam.getDcId());
		//获得生成代码信息
		List<CodeFile> resultList =
				generatorService.generate(generatorParam,dataSourceConfig);

		String json = JsonUtil.toJsonString(resultList);

		return ResultUtil.buildModelAndView(json);
	}
}
