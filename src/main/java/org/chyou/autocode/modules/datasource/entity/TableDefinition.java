package org.chyou.autocode.modules.datasource.entity;

import java.util.Collections;
import java.util.List;

/**
 * 数据库表定义
 */
public class TableDefinition {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 字段定义信息
	 */
	private List<ColumnDefinition> columnDefinitions;
	
	public TableDefinition(String tableName) {
		this(tableName, Collections.<ColumnDefinition> emptyList());
	}

	public TableDefinition(String tableName,
                           List<ColumnDefinition> columnDefinitions) {
		this.tableName = tableName;
		this.columnDefinitions = columnDefinitions;
	}
	
	public ColumnDefinition getPkColumn(){
		for (ColumnDefinition column : columnDefinitions) {
			if(column.getIsPk()){
				return column;
			}
		}
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
	}

	public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}

}
