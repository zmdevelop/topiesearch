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
import com.dm.search.model.SearchDataSource;
import com.dm.search.service.DataSourceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/dataSource")
public class DataSourceController {

	private Logger log = LoggerFactory.getLogger(DataSourceController.class);
	@Autowired
	private DataSourceService dataSourceService;

	@RequestMapping("/page")
	public String page(){
		return "search/dataSourcePage";
	}
	@RequestMapping("/list")
	@ResponseBody
	public Object list(Integer pageSize, Integer pageNum,
			HttpServletRequest request) {
		Map searchMap = SearchConditionUtil.packageSearchCondion(request);
		PageInfo<SearchDataSource> page = dataSourceService.list(pageSize,
				pageNum, searchMap);
		return PageConvertUtil.grid(page);
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(SearchDataSource dataSource) {
		int i = 0;
		if (dataSource.getId() == null) {
			i = this.dataSourceService.add(dataSource);
		} else {
			i = this.dataSourceService.update(dataSource);
		}
		if (i != 0)
			return ResponseUtil.success();
		return ResponseUtil.error();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id){
		int i = this.dataSourceService.delete(id);
		if (i != 0)
			return ResponseUtil.success();
		return ResponseUtil.error();
	}
	@RequestMapping("/load")
	@ResponseBody
	public Object load(Integer id){
		return this.dataSourceService.load(id);
	}
	@RequestMapping("/loadAll")
	@ResponseBody
	public Object loadAll(){
		Map searchMap = new HashMap();
		PageInfo<SearchDataSource> page = dataSourceService.list(100,
				1, searchMap);
		List<Map> list = new ArrayList<Map>();
		for(SearchDataSource s:page.getList()){
			Map map = new HashMap();
			map.put("text", s.getName());
			map.put("value", s.getName());
			list.add(map);
		}
		return list;
	}
}
