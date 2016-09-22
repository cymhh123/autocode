package org.chyou.autocode.modules.tpl.controller;

import org.chyou.autocode.common.AutoCodeContext;
import org.chyou.autocode.common.SearchEasyUI;
import org.durcframework.controller.CrudController;
import org.durcframework.expression.ExpressionQuery;
import org.durcframework.expression.subexpression.ValueExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.chyou.autocode.modules.tpl.entity.TemplateConfig;
import org.chyou.autocode.modules.tpl.entity.TemplateConfigSch;
import org.chyou.autocode.modules.tpl.service.TemplateConfigService;
import org.chyou.autocode.modules.user.entity.BackUser;

/**
 * 模板Controller
 */
@Controller
public class TemplateConfigController extends
        CrudController<TemplateConfig, TemplateConfigService> {

	/**
	 * 添加模板
	 * @param templateConfig
	 * @return
	 */
	@RequestMapping("/addTemplate.do")
	public ModelAndView addTemplate(TemplateConfig templateConfig) {
		BackUser user = AutoCodeContext.getInstance().getUser();
		templateConfig.setBackUser(user.getUsername());
		return this.save(templateConfig);
	}

	/**
	 * 模板列表
	 * @param searchEntity
	 * @return
	 */
	@RequestMapping("/listTemplate.do")
	public ModelAndView listTemplate(SearchEasyUI searchEntity) {
		BackUser user = AutoCodeContext.getInstance().getUser();
		searchEntity.setSortname("`name`");
		ExpressionQuery query = searchEntity.buildExpressionQuery();
		query.add(new ValueExpression("back_user", user.getUsername()));
		return this.query(query);
	}

	/**
	 * 更新模板
	 * @param templateConfig
	 * @return
	 */
	@RequestMapping("/updateTemplate.do")
	public ModelAndView updateTemplate(TemplateConfig templateConfig) {
		return this.update(templateConfig);
	}

	/**
	 * 删除模板
	 * @param templateConfig
	 * @return
	 */
	@RequestMapping("/delTemplate.do")
	public ModelAndView delTemplate(TemplateConfig templateConfig) {
		return this.delete(templateConfig);
	}

	/**
	 * 查询当前用户所有模板
	 * @param searchEntity
	 * @return
	 */
	@RequestMapping("/listUserTepmlate.do")
	public ModelAndView listUserTepmlate(TemplateConfigSch searchEntity) {
		searchEntity.setSortname("`name`");
		BackUser user = AutoCodeContext.getInstance().getUser();
		ExpressionQuery query = searchEntity.buildExpressionQuery();
		query.add(new ValueExpression("back_user", user.getUsername()));
		return this.query(query);
	}

}
