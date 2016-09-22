package org.chyou.autocode.modules.datasource.selector.mysql;


import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;
import org.chyou.autocode.modules.datasource.selector.TableSelector;

/**
 * 查询mysql数据库表
 */
public class MySqlTableSelector extends TableSelector {
	
	public MySqlTableSelector(DataBaseConfig dataBaseConfig) {
		super(dataBaseConfig);
	}

	@Override
	protected String getShowTablesSQL() {
		return "SHOW TABLES";
	}

}
