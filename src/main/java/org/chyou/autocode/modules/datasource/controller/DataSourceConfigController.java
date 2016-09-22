package org.chyou.autocode.modules.datasource.controller;


import org.chyou.autocode.common.AutoCodeContext;
import org.chyou.autocode.modules.datasource.entity.DatasourceConfigSch;
import org.chyou.autocode.modules.datasource.service.DataSourceConfigService;
import org.chyou.autocode.modules.datasource.utils.DBConnect;
import org.durcframework.controller.CrudController;
import org.durcframework.expression.ExpressionQuery;
import org.durcframework.expression.subexpression.ValueExpression;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;
import org.chyou.autocode.modules.user.entity.BackUser;

/**
 * 数据源Controller
 */
@Controller
public class DataSourceConfigController extends
        CrudController<DataSourceConfig, DataSourceConfigService> {

	/**
	 * 添加数据源
	 * @param dataSourceConfig
	 * @return
	 */
	@RequestMapping("/addDataSource.do")
	public ModelAndView addDataSource(DataSourceConfig dataSourceConfig) {
		//获得后台登录用户
		BackUser user = AutoCodeContext.getInstance().getUser();
		dataSourceConfig.setBackUser(user.getUsername());
		return this.save(dataSourceConfig);
	}

	/**
	 * 数据源列表
	 * @param searchEntity
	 * @return
	 */
	@RequestMapping("/listDataSource.do")
	public ModelAndView listDataSource(DatasourceConfigSch searchEntity) {
		BackUser user = AutoCodeContext.getInstance().getUser();
		ExpressionQuery query = searchEntity.buildExpressionQuery();
		query.add(new ValueExpression("back_user", user.getUsername()));
		return this.query(query);
	}

	/**
	 * 更新数据源
	 * @param dataSourceConfig
	 * @return
	 */
	@RequestMapping("/updateDataSource.do")
	public ModelAndView updateDataSource(DataSourceConfig dataSourceConfig) {
		return this.update(dataSourceConfig);
	}

	/**
	 * 删除数据源
	 * @param dataSourceConfig
	 * @return
	 */
	@RequestMapping("/delDataSource.do")
	public ModelAndView delDataSource(DataSourceConfig dataSourceConfig) {
		return this.delete(dataSourceConfig);
	}

	/**
	 * 链接数据源测试
	 * @param dataSourceConfig
	 * @return
	 */
	@RequestMapping("/connectionTest.do")
	public ModelAndView connectionTest(DataSourceConfig dataSourceConfig) {
		String connectInfo = DBConnect.testConnection(dataSourceConfig);

		if (StringUtils.hasText(connectInfo)) {
			return error(connectInfo);
		}

		return success();

	}

}
