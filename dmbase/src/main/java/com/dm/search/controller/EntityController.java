package com.dm.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.platform.util.PageConvertUtil;
import com.dm.platform.util.ResponseUtil;
import com.dm.platform.util.SearchConditionUtil;
import com.dm.search.model.SearchEntity;
import com.dm.search.service.EntityService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/entity")
public class EntityController {

	private Logger log = LoggerFactory.getLogger(EntityController.class);
	@Autowired
	private EntityService entityService;

	@RequestMapping("/page")
	public String page(){
		return "search/entityPage";
	}
	@RequestMapping("/addFolderPage") 
	public String addpage(){
		return "search/entity-addFolderPage";
	}
	@RequestMapping("/list")
	@ResponseBody
	public Object list(Integer pageSize, Integer pageNum,
			HttpServletRequest request) {
		Map searchMap = SearchConditionUtil.packageSearchCondion(request);
		PageInfo<SearchEntity> page = entityService.list(pageSize,
				pageNum, searchMap);
		return PageConvertUtil.grid(page);
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(SearchEntity dataSource) {
		int i = 0;
		if (dataSource.getId() == null) {
			i = this.entityService.add(dataSource);
		} else {
			i = this.entityService.update(dataSource);
		}
		if (i != 0)
			return ResponseUtil.success();
		return ResponseUtil.error();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id){
		int i = this.entityService.delete(id);
		if (i != 0)
			return ResponseUtil.success();
		return ResponseUtil.error();
	}
	@RequestMapping("/load")
	@ResponseBody
	public Object load(Integer id){
		return this.entityService.load(id);
	}
	@RequestMapping("/loadAll")
	@ResponseBody
	public Object loadAll(){
		Map searchMap = new HashMap();
		PageInfo<SearchEntity> page = entityService.list(100,
				1, searchMap);
		List<Map> list = new ArrayList<Map>();
		for(SearchEntity s:page.getList()){
			Map map = new HashMap();
			map.put("text", s.getEntityName());
			map.put("value", s.getId());
			list.add(map);
		}
		return list;
	}
}
