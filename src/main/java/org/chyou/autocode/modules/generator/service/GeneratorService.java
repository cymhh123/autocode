package org.chyou.autocode.modules.generator.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;
import org.chyou.autocode.modules.datasource.entity.SQLContext;
import org.chyou.autocode.modules.datasource.selector.SQLService;
import org.chyou.autocode.modules.datasource.selector.SQLServiceFactory;
import org.chyou.autocode.modules.tpl.entity.TemplateConfig;
import org.chyou.autocode.modules.tpl.service.TemplateConfigService;
import org.chyou.autocode.modules.tpl.utils.VelocityUtil;
import org.chyou.autocode.modules.generator.entity.CodeFile;
import org.chyou.autocode.modules.generator.entity.GeneratorParam;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成service
 */
@Service
public class GeneratorService {
	@Autowired
	private TemplateConfigService templateConfigService;

	/**
	 * 生成代码内容
	 * @param dataSourceConfig
	 * @return 一张表对应多个模板
	 */
	public List<CodeFile> generate(GeneratorParam generatorParam, DataSourceConfig dataSourceConfig){
		//获得对应的sqlService
		SQLService service = SQLServiceFactory.build(dataSourceConfig);
		//获得sql上线文环境
		List<SQLContext> contextList = service
				.getSqlContextBuilder(dataSourceConfig)
				.buildSQLContextList(generatorParam.getTableNames());
		
		List<CodeFile> codeFileList = new ArrayList<CodeFile>();

		//循环生成模板内容
		for (SQLContext sqlContext : contextList) {
			//设置包名
			setPackageName(sqlContext,generatorParam.getPackageName());
			
			String tableName = sqlContext.getTableDefinition().getTableName();
			
			for (int tcId : generatorParam.getTcIds()) {
				//获得模板
				TemplateConfig template = templateConfigService.get(tcId);
				//生成模板内容
				String content = doGenerator(sqlContext,template.getContent());
				//代码模板bean
				CodeFile codeFile = new CodeFile(tableName, template.getName(), content);
				codeFileList.add(codeFile);
			}
		}
		
		return codeFileList;
	}

    /**
     * 设置包名
	 * @param sqlContext
     * @param packageName
	 */
	private void setPackageName(SQLContext sqlContext,String packageName){
		if(StringUtils.hasText(packageName)){
			sqlContext.setPackageName(packageName);
		}
	}

	/**
	 * 生成模板内容
	 * @param sqlContext sql上下文（表信息，包名）
	 * @param template 模板
	 * @return
	 */
	private String doGenerator(SQLContext sqlContext,String template){
		StringWriter stringWriter = new StringWriter();
		BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
		try {
			//生成模板临时文件
			String tplFileStr = this.getClassPath() + File.separator + "temp.tpl";
			File tplFile = new File(tplFileStr);
			if(!tplFile.exists()){
				tplFile.createNewFile();
			}
			FileUtils.write(tplFile,template);

			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setDirectoryForTemplateLoading(new File(this.getClassPath()));
			Template temp = cfg.getTemplate("temp.tpl");

			Map context = new HashMap<>();
			context.put("context", sqlContext);
			context.put("table", sqlContext.getTableDefinition());
			context.put("pkColumn", sqlContext.getTableDefinition().getPkColumn());
			context.put("columns", sqlContext.getTableDefinition().getColumnDefinitions());

			temp.process(context, bufferedWriter);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
//		VelocityContext context = new VelocityContext();
//		context.put("context", sqlContext);
//		context.put("table", sqlContext.getTableDefinition());
//		context.put("pkColumn", sqlContext.getTableDefinition().getPkColumn());
//		context.put("columns", sqlContext.getTableDefinition().getColumnDefinitions());
		return stringWriter.toString();
	}

	public String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst(
				"WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}
}
