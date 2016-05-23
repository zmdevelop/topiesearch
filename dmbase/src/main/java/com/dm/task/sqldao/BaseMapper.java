package com.dm.task.sqldao;

import java.util.List;

public interface BaseMapper<T> {
	List<T> selectAddData(T entity);

	List<T> selectUpdateData(T entity);

	int deleteByPrimaryKey(Integer id);

	int insert(T entity);

	int insertSelective(T entity);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T entity);

	int updateByPrimaryKey(T entity);
}
