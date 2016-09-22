package org.chyou.autocode.modules.datasource.selector;

import org.chyou.autocode.modules.datasource.entity.ColumnDefinition;
import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;
import org.chyou.autocode.modules.datasource.entity.SQLContext;
import org.chyou.autocode.modules.datasource.entity.TableDefinition;
import org.chyou.autocode.modules.datasource.utils.SqlHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sqlContext生成器
 */
public abstract class SqlContextBuilder {

	private DataBaseConfig dataBaseConfig;
	
	public SqlContextBuilder(DataBaseConfig dataBaseConfig){
		this.dataBaseConfig = dataBaseConfig;
	}

	/**
	 * 表结构语句
	 * 返回查询表信息的SQL语句
	 * 不同的数据查询表信息不一样
	 * 如mysql是DESC tableName
	 * @return
	 */
	protected abstract String getTableDetailSQL(String tableName);
	
	/**
	 * 构造字段信息
	 * @param rowMap
	 * @return
	 */
	protected abstract ColumnDefinition buildColumnDefinition(Map<String, Object> rowMap);

	/**
	 * 生成sqlcontext信息环境
	 * @param tableNames 表名列表
	 * @return
	 */
	public List<SQLContext> buildSQLContextList(List<String> tableNames) {
		List<SQLContext> contexts = new ArrayList<SQLContext>();
		//封装表，字段信息
		for (String tableName : tableNames) {
			List<Map<String, Object>> resultList = SqlHelper.runSql(this.getDataBaseConfig(), getTableDetailSQL(tableName));
			
			List<ColumnDefinition> columnDefinitionList = new ArrayList<ColumnDefinition>();
			// 构建columnDefinition
			for (Map<String, Object> rowMap : resultList) {
				columnDefinitionList.add(buildColumnDefinition(rowMap));
			}
			
			TableDefinition tableDefinition = new TableDefinition(tableName,columnDefinitionList);
			SQLContext context = new SQLContext(tableDefinition);
						
			contexts.add(context);
		}
	
		return contexts;
	}

	public DataBaseConfig getDataBaseConfig() {
		return dataBaseConfig;
	}

	public void setDataBaseConfig(DataBaseConfig dataBaseConfig) {
		this.dataBaseConfig = dataBaseConfig;
	}

}
