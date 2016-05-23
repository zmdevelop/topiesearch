package com.dm.search.service;

import java.util.Map;

import com.dm.search.model.SearchDataSource;
import com.github.pagehelper.PageInfo;

public interface DataSourceService {
	int add(SearchDataSource dataSource);

	int update(SearchDataSource dataSource);

	int delete(Integer id);
	
	SearchDataSource load(Integer id);
	
	PageInfo<SearchDataSource> list(Integer pageSize,Integer PageNum,Map searchMap);
	 
}
