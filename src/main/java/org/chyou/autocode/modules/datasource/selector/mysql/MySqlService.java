package org.chyou.autocode.modules.datasource.selector.mysql;


import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;
import org.chyou.autocode.modules.datasource.selector.SQLService;
import org.chyou.autocode.modules.datasource.selector.SqlContextBuilder;
import org.chyou.autocode.modules.datasource.selector.TableSelector;

/**
 * Mysql对外服务
 */
public class MySqlService implements SQLService {

	@Override
	public TableSelector getTableSelector(DataBaseConfig dataBaseConfig) {
		return new MySqlTableSelector(dataBaseConfig);
	}

	@Override
	public SqlContextBuilder getSqlContextBuilder(DataBaseConfig dataBaseConfig) {
		return new MysqlSqlContextBuilder(dataBaseConfig);
	}


}
