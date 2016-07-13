package com.dm.dynamicbean.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.dynamicbean.model.Column;
import com.dm.dynamicbean.model.DataBase;
import com.dm.dynamicbean.model.Table;
import com.dm.dynamicbean.service.ShowSchemaService;
import com.dm.search.model.SearchDataSource;
import com.dm.search.service.DataSourceService;

@Controller
public class ShowSchemaController {
	
	@Autowired
	private ShowSchemaService schemaService;
	@Autowired
	private DataSourceService dataSource;
	
	@RequestMapping("/getSchema")
	@ResponseBody
	public Object showSchema(String dataSourceName){
		SearchDataSource ds = dataSource.loadByName(dataSourceName);
		List<DataBase> list = schemaService.getSchema(ds);
		List s = new ArrayList();
		for(DataBase d:list){
			Map map = new HashMap();
			map.put("text", d.getSchemaName());
			map.put("value", d.getSchemaName());
			s.add(map);
		}
		return s;
	}
	@RequestMapping("/getTables")
	@ResponseBody
	public Object getTables(String dataSourceName,@RequestParam(required=false)String schemaName){
		SearchDataSource ds = dataSource.loadByName(dataSourceName);
		List<Table> list = schemaService.getTables(ds,schemaName);
		List s = new ArrayList();
		for(Table d:list){
			Map map = new HashMap();
			map.put("text", d.getTableName());
			map.put("value", d.getTableName());
			s.add(map);
		}
		return s;
	}
	@RequestMapping("/getColumn")
	@ResponseBody
	public Object showColumn(String dataSourceName,@RequestParam(required=false)String schemaName,String tableName){
		SearchDataSource ds = dataSource.loadByName(dataSourceName);
		List<Column> list = schemaService.getColumn(ds,schemaName,tableName);
		List s = new ArrayList();
		for(Column d:list){
			Map map = new HashMap();
			map.put("text", d.getTableName() +"."+ d.getColumnName());//+"("+d.getDataType()+")");
			map.put("value", d.getTableName() +"."+d.getColumnName());
			s.add(map);
		}
		return s;
	}

}
