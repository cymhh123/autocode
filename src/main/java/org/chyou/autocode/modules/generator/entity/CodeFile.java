package org.chyou.autocode.modules.generator.entity;

/**
 * 代码模板
 */
public class CodeFile {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 模板名
	 */
	private String templateName;
	/**
	 * 生成的模板内容
	 */
	private String content;

	public CodeFile(String tableName, String templateName, String content) {
		this.tableName = tableName;
		this.templateName = templateName;
		this.content = content;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
