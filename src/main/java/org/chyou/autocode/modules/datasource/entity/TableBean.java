package org.chyou.autocode.modules.datasource.entity;

public class TableBean {
	
	public TableBean(String tableName) {
		this.tableName = tableName;
	}

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}