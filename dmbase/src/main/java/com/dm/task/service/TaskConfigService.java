package com.dm.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.task.model.TaskConfig;
import com.dm.task.sqldao.TaskConfigMapper;

@Service
public class TaskConfigService {
	
	@Autowired
	TaskConfigMapper taskConfigMapper;
	
	
	public boolean save(TaskConfig taskConfig)
	{
		taskConfigMapper.insert(taskConfig);
		return true;
	}
	
	public boolean update(TaskConfig taskConfig)
	{
		taskConfigMapper.updateByPrimaryKeySelective(taskConfig);
		return true;
	}
	
	public TaskConfig get(String id)
	{
		return taskConfigMapper.selectByPrimaryKey(id);
	}

}
