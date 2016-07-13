package com.dm.dynamicbean.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.dynamicbean.dao.IDataBaseDao;
import com.dm.dynamicbean.model.Column;
import com.dm.dynamicbean.model.DataBase;
import com.dm.dynamicbean.model.Table;
import com.dm.dynamicbean.service.ShowSchemaService;
import com.dm.search.model.SearchDataSource;

@Service
public class ShowSchemaServiceImp implements ShowSchemaService{

	@Autowired
	private IDataBaseDao dataBaseDao;
	
	@Override
	public List<DataBase> getSchema(SearchDataSource ds) {
		return dataBaseDao.getDataBaseListByAddress(ds);
	}

	@Override
	public List<Table> getTables(SearchDataSource ds, String schemaName) {
		return dataBaseDao.getTableListByAddressAndDatabase(ds, schemaName);
	}

	@Override
	public List<Column> getColumn(SearchDataSource ds, String schemaName, String tableName) {
		return this.dataBaseDao.getColumnListByAddressAndDatabase(ds,schemaName, tableName);
	}

}
