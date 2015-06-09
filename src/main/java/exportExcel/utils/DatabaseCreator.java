/*
 * Copyright (C) CCRISE.
 */
package exportExcel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库不存在时自动创建数据库.
 * <p>
 * 支持：
 * <li>MySQL</li>
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
public class DatabaseCreator {
	/**
	 * 日志.
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * jdbcURL.
	 */
	private String jdbcURL;
	/**
	 * jdbcUsername.
	 */
	private String jdbcUsername;
	/**
	 * jdbcPassword.
	 */
	private String jdbcPassword;
	/**
	 * jdbcDriver.
	 */
	private String jdbcDriver;

	/**
	 * 初始化数据库.
	 */
	public void init() {
		config();
		String databaseName = null;

		if (jdbcURL.contains("mysql")) {
			if (jdbcURL.indexOf("?") == -1) {
				databaseName = jdbcURL.substring(jdbcURL.lastIndexOf("/") + 1);
			} else {
				databaseName = jdbcURL.substring(jdbcURL.lastIndexOf("/") + 1, jdbcURL.indexOf("?"));
			}

			try {
				Class.forName(jdbcDriver);
				Connection connection = DriverManager.getConnection(jdbcURL.substring(0, jdbcURL.lastIndexOf("/")),
						jdbcUsername, jdbcPassword);
				Statement statement = connection.createStatement();
				statement.execute("CREATE DATABASE `" + databaseName + "`");
				connection.close();
				logger.debug("创建MySQL数据库：{}成功", databaseName);
			} catch (SQLException e) {
				logger.debug("MySQL数据库：{}已存在", databaseName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (jdbcURL.contains("sqlserver")) {
			databaseName = jdbcURL.substring(jdbcURL.lastIndexOf("=") + 1);
			logger.debug("暂不支持SQL Server数据库的创建");
		}
	}

	/**
	 * 根据读取配置.
	 */
	private void config() {
		jdbcURL = PropertiesUtils.getString(PropertiesUtils.JDBC_URL_PROPERTY);
		jdbcUsername = PropertiesUtils.getString(PropertiesUtils.JDBC_USERNAME_PROPERTY);
		jdbcPassword = PropertiesUtils.getString(PropertiesUtils.JDBC_PASSWORD_PROPERTY);
		jdbcDriver = PropertiesUtils.getString(PropertiesUtils.JDBC_DRIVER_PROPERTY);
	}
}
