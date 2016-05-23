package com.dm.search.service;

import java.util.Map;

import com.dm.search.model.SearchEntity;
import com.github.pagehelper.PageInfo;

public interface EntityService {
	int add(SearchEntity record);

	int update(SearchEntity record);

	int delete(Integer id);
	
	SearchEntity load(Integer id);
	
	PageInfo<SearchEntity> list(Integer pageSize,Integer PageNum,Map searchMap);
	 
}
