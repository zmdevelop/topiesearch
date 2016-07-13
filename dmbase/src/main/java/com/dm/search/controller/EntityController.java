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
import org.springframework.web.servlet.ModelAndView;

import com.dm.platform.util.PageConvertUtil;
import com.dm.platform.util.ResponseUtil;
import com.dm.platform.util.SearchConditionUtil;
import com.dm.search.model.SearchDataSource;
import com.dm.search.model.SearchEntity;
import com.dm.search.service.DataSourceService;
import com.dm.search.service.EntityService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/entity")
public class EntityController {

	private Logger log = LoggerFactory.getLogger(EntityController.class);
	@Autowired
	private EntityService entityService;
	@Autowired
	private DataSourceService dataSourceService;

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
	@RequestMapping("/addpage")
	public String add(ModelAndView model){
		//Map map = new HashMap();
		/*List<SearchDataSource> list = dataSourceService.listAll(map);
		model.addObject("dataSourceList", list);*/
		return "search/addentity";
	}
	@RequestMapping("/showInfo")
	public ModelAndView info(Integer id,ModelAndView model){
		SearchEntity entity = this.entityService.load(id);
		model.addObject("entity", entity);
		if(entity.getPid()!=0){
			SearchEntity parent = this.entityService.load(entity.getPid());
			model.addObject("parentName", parent.getEntityName());
			model.setViewName("search/entity-wd-InfoPage");
			return model;
		}
		model.setViewName("search/entity-db-InfoPage");
		return model;
	}
	@RequestMapping("updateStatus")
	@ResponseBody
	public Object updateStatus(Integer id){
		boolean f = this.entityService.updateStatus(id);
		if(f){
			return ResponseUtil.success("操作成功!");
		}
		return ResponseUtil.error("操作失败!");
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
