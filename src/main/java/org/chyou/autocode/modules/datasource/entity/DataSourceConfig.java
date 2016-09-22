package org.chyou.autocode.modules.datasource.entity;


/**
 * 数据源配置信息
 */
public class DataSourceConfig extends DataBaseConfig {
	/**
	 * 数据源id
	 */
	private int dcId;
	/**
	 * 数据源别名
	 */
	private String name;
	/**
	 * 创建人
	 */
	private String backUser;

	public int getDcId() {
		return dcId;
	}

	public void setDcId(int dcId) {
		this.dcId = dcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBackUser() {
		return backUser;
	}

	public void setBackUser(String backUser) {
		this.backUser = backUser;
	}

}
