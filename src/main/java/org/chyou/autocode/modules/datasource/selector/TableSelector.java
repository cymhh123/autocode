package org.chyou.autocode.modules.datasource.selector;



import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;
import org.chyou.autocode.modules.datasource.entity.TableBean;
import org.chyou.autocode.modules.datasource.utils.SqlHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class TableSelector {

	private DataBaseConfig dataBaseConfig;

	public TableSelector(DataBaseConfig dataBaseConfig) {
		this.dataBaseConfig = dataBaseConfig;
	}

	/**
	 * 查询数据库表的SQL
	 * 
	 * @return
	 */
	protected abstract String getShowTablesSQL();

	/**
	 * 返回表结构
	 * 
	 * @return
	 */
	public List<TableBean> getTableList() {
		List<Map<String, Object>> resultList = SqlHelper.runSql(getDataBaseConfig(), getShowTablesSQL());
		List<TableBean> tableNames = new ArrayList<TableBean>();
		
		for (Map<String, Object> rowMap : resultList) {
			Set<String> keySet = rowMap.keySet();
			for (String key : keySet) {
				String tableName = (String)rowMap.get(key);
				tableNames.add(new TableBean(tableName));
			}
		}
		
		return tableNames;
	}

	public DataBaseConfig getDataBaseConfig() {
		return dataBaseConfig;
	}

	public void setDataBaseConfig(DataBaseConfig dataBaseConfig) {
		this.dataBaseConfig = dataBaseConfig;
	}

}
