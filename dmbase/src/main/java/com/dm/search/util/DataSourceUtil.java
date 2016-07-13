package com.dm.search.util;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.dm.search.model.SearchDataSource;

public class DataSourceUtil {
	private static Logger logger = Logger.getLogger(DataSourceUtil.class);  
	  
    private static DataSourceUtil instance;  
  
    private DruidDataSource datasource;  
  
    private DataSourceUtil(String driverClass,String jdbcUrl,String user,String password) {  
    try {  
        datasource = new DruidDataSource();  
        // 数据源进行各种有效的控制：  
        // 设置驱动  
        datasource.setDriverClassName(driverClass);  
        // 设置数据库URL  
        datasource.setUrl(jdbcUrl);  
        // 设置用户名  
        datasource.setUsername(user);  
        // 设置密码  
        datasource  
            .setPassword(password);  
    } catch (Exception e) {  
        logger.info("创建数据源出现异常," + e);  
    }  
    }  
  
    public static final DataSourceUtil getInstance(SearchDataSource dataSource) {  
    if (instance == null) {  
        try {  
        instance = new DataSourceUtil(dataSource.getDriver(),dataSource.getUrl(),dataSource.getUser(),dataSource.getPassword());  
        } catch (Exception e) {  
        logger.info("创建数据源出现异常," + e);  
        }  
    }  
    return instance;  
    }  
  
    public synchronized final DruidPooledConnection getConnection() {  
    try {  
        // 获取数据连接  
        return datasource.getConnection();  
    } catch (Exception e) {  
        logger.info("无法从数据源获取连接," + e);  
    }  
    return null;  
    }  
  
}
