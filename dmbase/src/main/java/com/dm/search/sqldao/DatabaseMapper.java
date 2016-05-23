package com.dm.search.sqldao;

import java.util.List;
import java.util.Map;

public interface DatabaseMapper {
	List<Map> selectTables(String database);
	List<Map> selectColumns(String database,String tableName);
}
