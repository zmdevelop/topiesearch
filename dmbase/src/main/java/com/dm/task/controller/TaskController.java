package com.dm.task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.task.model.TaskConfig;
import com.dm.task.service.TaskConfigService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	TaskConfigService taskConfigService;
	
	@RequestMapping("/configPage")
	public String taskConfig(Model model,String id)
	{
		model.addAttribute("taskConfig", taskConfigService.get("123"));
		return "node-config";
	}
	
	@RequestMapping("/insertOrUpdate")
	@ResponseBody
	public Map sve(Model model,TaskConfig taskConfig)
	{
		Map map = new HashMap();
		taskConfig.setId("123");
		if(taskConfig.getId()!=null && !taskConfig.getId().equals(""))
		{
			taskConfigService.update(taskConfig);
		}
		else
		{
			taskConfigService.save(taskConfig);
		}
		map.put("msg","配置成功");
		return map;
	}
}
