package com.dm.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.search.model.SearchDataSource;
import com.dm.search.service.DataSourceService;
import com.dm.search.sqldao.SearchDataSourceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DataSourceServiceImpl implements DataSourceService {
	
	@Autowired
	private SearchDataSourceMapper dataSourceMapper;

	@Override
	public int add(SearchDataSource dataSource) {
		init(dataSource);
		return dataSourceMapper.insert(dataSource);
	}

	private void init(SearchDataSource dataSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(SearchDataSource dataSource) {
		// TODO Auto-generated method stub
		return dataSourceMapper.updateByPrimaryKeySelective(dataSource);
	}

	@Override
	public int delete(Integer id) {
		return dataSourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SearchDataSource load(Integer id) {
		return this.dataSourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<SearchDataSource> list(Integer pageSize, Integer pageNum,
			Map searchMap) {
		PageHelper.startPage(pageNum, pageSize);
		List<SearchDataSource> list = this.dataSourceMapper.listByArg(searchMap);
		PageInfo<SearchDataSource> page = new PageInfo<SearchDataSource>(list);
		return page;
	}

}
