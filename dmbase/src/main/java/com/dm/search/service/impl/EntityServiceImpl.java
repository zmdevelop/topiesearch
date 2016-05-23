package com.dm.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.search.model.SearchEntity;
import com.dm.search.service.DataSourceService;
import com.dm.search.service.EntityService;
import com.dm.search.sqldao.SearchEntityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EntityServiceImpl implements EntityService {
	
	@Autowired
	private SearchEntityMapper entityMapper;

	@Override
	public int add(SearchEntity entity) {
		init(entity);
		return entityMapper.insert(entity);
	}

	private void init(SearchEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(SearchEntity entity) {
		// TODO Auto-generated method stub
		return entityMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int delete(Integer id) {
		return entityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SearchEntity load(Integer id) {
		return this.entityMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<SearchEntity> list(Integer pageSize, Integer pageNum,
			Map searchMap) {
		PageHelper.startPage(pageNum, pageSize);
		List<SearchEntity> list = this.entityMapper.listByArg(searchMap);
		PageInfo<SearchEntity> page = new PageInfo<SearchEntity>(list);
		return page;
	}

}
