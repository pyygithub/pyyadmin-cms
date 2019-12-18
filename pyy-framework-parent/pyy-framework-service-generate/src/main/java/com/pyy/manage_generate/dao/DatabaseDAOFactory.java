package com.pyy.manage_generate.dao;

import com.pyy.framework.domain.generate.DataSource;
import com.pyy.manage_generate.constants.DBMSConstants;
import com.pyy.manage_generate.dao.impl.CommonDatabaseDAOImpl;
import com.pyy.manage_generate.dao.impl.MySql5DatabaseDAO;
import com.pyy.manage_generate.dao.sql.DatabaseType;

/**
 * ========================
 * 查询器生成工厂
 * Author：pyy
 * Date：2019-9-25 16:22:57
 * Version: v1.0
 * ========================
 */
public class DatabaseDAOFactory {

	public static IDatabaseDAO getDAO(DataSource dataSource) {
		String upperCaseDbName = dataSource.getDbType().toUpperCase();
		
		if (upperCaseDbName.contains(DBMSConstants.ORACLE)) {
			return new CommonDatabaseDAOImpl(dataSource, DatabaseType.Oracle);
		}  else if (upperCaseDbName.contains(DBMSConstants.SQL_SERVER) || upperCaseDbName.contains(DBMSConstants.SQLSERVER)) {
			return new CommonDatabaseDAOImpl(dataSource, DatabaseType.MSSQLServer);
		} else if (upperCaseDbName.contains(DBMSConstants.MYSQL)) {
			return new MySql5DatabaseDAO(dataSource, DatabaseType.MySql5);
		}
		return new MySql5DatabaseDAO(dataSource, DatabaseType.MySql5);
	}
}
