package com.dm.search.sqldao;

import java.util.List;
import java.util.Map;

import com.dm.search.model.SearchDataSource;

public interface SearchDataSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchDataSource record);

    int insertSelective(SearchDataSource record);

    SearchDataSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchDataSource record);

    int updateByPrimaryKey(SearchDataSource record);

	List<SearchDataSource> listByArg(Map searchMap);

	SearchDataSource selectByAddress(String address);
}