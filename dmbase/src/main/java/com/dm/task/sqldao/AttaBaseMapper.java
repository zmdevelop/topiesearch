package com.dm.task.sqldao;

import java.util.List;

public interface AttaBaseMapper<T> {
	 	int insert(T record);

	    int insertSelective(T record);

		List<T> selectAttaList(Integer id);
		
		int deleteAttaByContentId(Integer id);
}
