package org.chyou.autocode.modules.generator.entity;

import java.util.List;

/**
 * 生成代码前台传递的参数
 */
public class GeneratorParam {
	/**
	 * 数据源id
	 */
	private int dcId;
	/**
	 * 表名列表
	 */
	private List<String> tableNames;
	/**
	 * 模板id列表
	 */
	private List<Integer> tcIds;
	/**
	 * 代码输出的包名
	 */
	private String packageName;
	

	public int getDcId() {
		return dcId;
	}

	public void setDcId(int dcId) {
		this.dcId = dcId;
	}

	public List<String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

	public List<Integer> getTcIds() {
		return tcIds;
	}

	public void setTcIds(List<Integer> tcIds) {
		this.tcIds = tcIds;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
