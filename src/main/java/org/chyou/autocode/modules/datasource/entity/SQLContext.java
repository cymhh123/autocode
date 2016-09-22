package org.chyou.autocode.modules.datasource.entity;


import org.chyou.autocode.modules.datasource.utils.FieldUtil;

import java.util.List;

/**
 * 封装表，字段信息，包
 * SQL上下文,这里可以取到表,字段信息
 * 最终会把SQL上下文信息放到velocity中
 */
public class SQLContext {
	/**
	 * 表结构定义（表名，字段列表）
	 */
	private TableDefinition tableDefinition;
	/**
	 * 包名
	 */
	private String packageName;
	
	public SQLContext(TableDefinition tableDefinition){
		this.tableDefinition = tableDefinition;
		// 默认为全字母小写的类名
		this.packageName = getJavaBeanName().toLowerCase();
	}
	
	public String getJavaBeanName(){
		String tableName = tableDefinition.getTableName();
		tableName = FieldUtil.underlineFilter(tableName);
		return FieldUtil.upperFirstLetter(tableName);
	}
	
	public String getPkName(){
		if(this.tableDefinition.getPkColumn() != null){
			return this.tableDefinition.getPkColumn().getColumnName();
		}
		return "";
	}
	
	public String getJavaPkName(){
		if(this.tableDefinition.getPkColumn() != null){
			return this.tableDefinition.getPkColumn().getJavaFieldName();
		}
		return "";
	}
	
	public String getJavaPkType(){
		if(this.tableDefinition.getPkColumn() != null){
			return this.tableDefinition.getPkColumn().getJavaType();
		}
		return "";
	}
	
	public String getMybatisPkType(){
		if(this.tableDefinition.getPkColumn() != null){
			return this.tableDefinition.getPkColumn().getMybatisJdbcType();
		}
		return "";
	}

	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}

	public void setTableDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
	}
	
	public List<ColumnDefinition> getColumnDefinitionList(){
		return tableDefinition.getColumnDefinitions();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
}
