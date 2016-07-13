package com.dm.search.util;

import java.util.List;

import com.dm.search.model.SearchDataSource;
import com.mysql.jdbc.Connection;

public abstract class MyDataSource {
	protected SearchDataSource dataSource;
	protected Connection conection;
	
	abstract List showDataBases();
	abstract List showTables(String database);
	abstract List showColumns(String database,String table);
}
