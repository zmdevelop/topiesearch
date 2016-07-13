package com.dm.dynamicbean.service;

import java.util.List;

import com.dm.dynamicbean.model.Column;
import com.dm.dynamicbean.model.DataBase;
import com.dm.dynamicbean.model.Table;
import com.dm.search.model.SearchDataSource; 

public interface ShowSchemaService {
 
	List<DataBase> getSchema(SearchDataSource ds);

	List<Table> getTables(SearchDataSource ds, String schemaName);

	List<Column> getColumn(SearchDataSource ds, String schemaName, String tableName);
	
	
}
