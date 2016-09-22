package org.chyou.autocode.modules.datasource.selector;


import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;

public interface SQLService {

	public TableSelector getTableSelector(DataBaseConfig dataBaseConfig);

	public SqlContextBuilder getSqlContextBuilder(DataBaseConfig dataBaseConfig);

}
