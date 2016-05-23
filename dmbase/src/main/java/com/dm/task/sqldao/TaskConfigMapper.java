package com.dm.task.sqldao;

import com.dm.task.model.TaskConfig;

public interface TaskConfigMapper {
    int insert(TaskConfig record);

    int insertSelective(TaskConfig record);
    
    TaskConfig selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(TaskConfig record);
}