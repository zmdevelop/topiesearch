package com.dm.dynamicbean.dao;

import java.util.List;

import com.dm.dynamicbean.model.Column;
import com.dm.dynamicbean.model.DataBase;
import com.dm.dynamicbean.model.Table;
import com.dm.search.model.SearchDataSource;

public interface IDataBaseDao {

	List<DataBase> getDataBaseListByAddress(String address);
	
	List<Table> getTableListByAddressAndDatabase(String address, String database);

	List<Column> getColumnListByAddressAndDatabase(String address, String database);

	DataBase getDataBaseByAddressAndDbName(String address, String dbName);
	
	List<DataBase> getDataBaseListByAddress(SearchDataSource dataSource);
	
	List<Table> getTableListByAddressAndDatabase(SearchDataSource dataSource, String database);

	List<Column> getColumnListByAddressAndDatabase(SearchDataSource dataSource,String schemaName, String database);

	DataBase getDataBaseByAddressAndDbName(SearchDataSource dataSource, String dbName);

}
