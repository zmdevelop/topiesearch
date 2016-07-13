package com.dm.search.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dm.platform.util.ResponseUtil;
import com.dm.search.model.SearchConfig;
import com.dm.search.model.SearchEntity;
import com.dm.search.service.EntityService;
import com.dm.search.service.SearchConfigService;
/**
 * @描述   搜索配置controller
 * @作者 dyt
 * @创建日期 2015-12-3
 * @版本 1.0
 */
@Controller
@RequestMapping("/searchConfig")
public class SearchController {
	
	private Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	SearchConfigService searchConfigService;
	@Autowired
	EntityService entityService;
	
	
	/**
	 * @描述 返回搜索配置主页面
	 * @参数 model
	 * @返回 ModelAndView
	 */
	@RequestMapping("/config")
	public ModelAndView searchConfigPage(ModelAndView model)
	{
		model.addObject("searchConfig", searchConfigService.findConfig("123"));
		model.setViewName("/search/search-index");
		return model;
	}
	
	@RequestMapping("/build")
	@ResponseBody
	public Object build(){
		int i = this.searchConfigService.build();
		return ResponseUtil.success("配置成功！");
	}
	
	@RequestMapping("/insertOrUpdate")
	@ResponseBody
	public Object insertOrUpdate(SearchConfig searchConfig)
    {
			boolean success = searchConfigService.insertOrUpdate(searchConfig);
			if(success)
			{
			log.info("cms配置:{},spider配置:{},attachment:配置{}!", searchConfig.getCmsContent(),searchConfig.getSpider(),searchConfig.getAttachment());
			}
			else
			{
			 log.info("配置失败！");
			 return ResponseUtil.success("配置失败！");
			}
			return ResponseUtil.success("配置成功！");
			
    }
	
	/**
	 * @decription 查询接口
	 * @param textValue
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/searchText")
	public ModelAndView searchText(
			@RequestParam(required=false,value="textValue")String textValue,
			@RequestParam(required=false,value="pageNum",defaultValue="1")Integer pageNum,
			@RequestParam(required=false,value="pageSize",defaultValue="10")Integer pageSize,
			@RequestParam(required=false,value="sortField",defaultValue="publishDate")String sortField,
			@RequestParam(required=false,value="titleValue")String titleValue,
			@RequestParam(required=false,value="contentValue")String contentValue,
			@RequestParam(required=false,value="entity")String entity,
			ModelAndView model,Device device)
    {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		long totalPage = 0;
		Map searchMap = new HashMap<>();
		searchMap.put("status", "1");
		List<SearchEntity> entityList = entityService.list( searchMap);
		model.addObject("entityList", entityList);
		model.addObject("entity",entity);
		if(textValue==null || textValue.equals(""))
		{	
			model.setViewName("/search/search-view");
			return model; 
		}
		Map map = searchConfigService.searchResults(textValue, pageNum, pageSize,sortField,entity,null,device);
		map.put("textValue", textValue);
		model.addAllObjects(map);
		/*model.addObject("contents", map.get("list"));
		model.addObject("total", map.get("total"));
		totalPage = (long)map.get("total")/pageSize;
		if(totalPage==0)
		{
			totalPage=1;
		}
		else if(((Long)map.get("total")%pageSize)>0)
		{
			totalPage+=1;
		}
		model.addObject("totalPage", totalPage);
		model.addObject("pageNum", pageNum);*/
		model.setViewName("/search/search-view");
		log.debug("{}--contents",map.get("contents"));
		return model;
    }
	@RequestMapping("/search")
	@ResponseBody
	public Object search(
			@RequestParam(required=true,value="text")String textValue,
			@RequestParam(required=false,value="pageNum",defaultValue="1")Integer pageNum,
			@RequestParam(required=false,value="pageSize",defaultValue="5")Integer pageSize,
			@RequestParam(required=false,value="days")Integer days,
			@RequestParam(required=false,value="sortField")String sortField,
			@RequestParam(required=false,value="entity")String entity,
			Device device)
    {
		Map map = new HashMap();
		
		if(textValue==null || textValue.equals(""))
		{
			map.put("status",0);
			map.put("mes", "请输入搜索关键词！");
			map.put("list", ListUtils.EMPTY_LIST);
			return map;
//			JSONObject jsonObject = JSONObject.fromObject(map);
//			return callback+":("+jsonObject.toString()+")";
//			return callback+":({'status':'0','mes', '请输入搜索关键词！'})";
		}
		 map = searchConfigService.searchResults(textValue, pageNum, pageSize,sortField,entity,days,device);
		return map;
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		return callback+":({'status':'0','mes', '请输入搜索关键词！'})";
//		return callback+":("+jsonObject.toString()+")";
    }
}
