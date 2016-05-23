package com.dm.search.sqldao;

import java.util.List;
import java.util.Map;

import com.dm.search.model.SearchEntity;

public interface SearchEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchEntity record);

    int insertSelective(SearchEntity record);

    SearchEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchEntity record);

    int updateByPrimaryKey(SearchEntity record);

	List<SearchEntity> listByArg(Map searchMap);
}