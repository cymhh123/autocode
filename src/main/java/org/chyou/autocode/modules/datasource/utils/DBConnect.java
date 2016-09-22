package org.chyou.autocode.modules.datasource.utils;


import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 */
public class DBConnect {

	/**
	 * 获取数据库连接
	 * @param config 数据库配置
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection(DataSourceConfig config) throws ClassNotFoundException, SQLException {
		Class.forName(config.getDriverClass());
		return DriverManager.getConnection(config.getJdbcUrl(),
				config.getUsername(), config.getPassword());
	}

	/**
	 * 测试连接,返回错误信息,无返回内容说明连接成功
	 * @return 返回错误信息,无返回内容说明连接成功
	 */
	public static String testConnection(DataSourceConfig dataSourceConfig) {
		Connection con = null;
		String ret = null;
		try {
			con = DBConnect.getConnection(dataSourceConfig);
			// 不为空说明连接成功
			if (con == null) {
				ret = dataSourceConfig.getName() + "连接失败";
			}
		} catch (ClassNotFoundException e) {
			ret = dataSourceConfig.getName() + "连接失败" + "<br>错误信息:"
					+ "找不到驱动" + dataSourceConfig.getDriverClass();
		} catch (SQLException e) {
			ret = dataSourceConfig.getName() + "连接失败" + "<br>错误信息:"
					+ e.getMessage();
		} finally {
			if (con != null) {
				try {
					con.close(); // 关闭连接,该连接无实际用处
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
}
