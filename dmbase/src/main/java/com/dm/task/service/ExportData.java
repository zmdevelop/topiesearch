package com.dm.task.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dm.platform.util.FileUtil;
import com.dm.task.R;
import com.dm.task.model.CmsAttachment;
import com.dm.task.model.CmsAttachmentOther;
import com.dm.task.model.CmsAudio;
import com.dm.task.model.CmsAudioAttach;
import com.dm.task.model.CmsChannel;
import com.dm.task.model.CmsContent;
import com.dm.task.model.CmsContentAttachment;
import com.dm.task.model.CmsNovel;
import com.dm.task.model.CmsNovelAttach;
import com.dm.task.model.CmsSite;
import com.dm.task.model.CmsVideo;
import com.dm.task.model.CmsVideoAttach;
import com.dm.task.model.TaskConfig;
import com.dm.task.model.UserAccount;
import com.dm.task.sqldao.CmsAudioAttachMapper;
import com.dm.task.sqldao.CmsContentAttachmentMapper;
import com.dm.task.sqldao.CmsNovelAttachMapper;
import com.dm.task.sqldao.CmsVideoAttachMapper;
import com.dm.task.sqldao.TaskCmsAttachmentMapper;
import com.dm.task.sqldao.TaskCmsAttachmentOtherMapper;
import com.dm.task.sqldao.TaskCmsAudioMapper;
import com.dm.task.sqldao.TaskCmsChannelMapper;
import com.dm.task.sqldao.TaskCmsContentMapper;
import com.dm.task.sqldao.TaskCmsNovelMapper;
import com.dm.task.sqldao.TaskCmsSiteMapper;
import com.dm.task.sqldao.TaskCmsVideoMapper;
import com.dm.task.sqldao.UserAccountMapper;
import com.dm.task.util.JsonDateValueProcessor;

@Component("exportData")
public class ExportData {
	@Autowired
	TaskCmsAttachmentMapper cmsAttachmentMapper;
	@Autowired
	TaskCmsAttachmentOtherMapper cmsAttachmentOtherMapper;
	@Autowired
	TaskCmsSiteMapper cmsSiteMapper;
	@Autowired
	TaskCmsChannelMapper cmsChannelMapper;
	@Autowired
	TaskCmsContentMapper cmsContentMapper;
	@Autowired
	TaskCmsNovelMapper cmsNovelMapper;
	@Autowired
	TaskCmsVideoMapper cmsVideoMapper;
	@Autowired
	TaskCmsAudioMapper cmsAudioMapper;
	@Autowired
	UserAccountMapper userAccountMapper;
	@Autowired
	CmsAudioAttachMapper cmsAutioAttachMapper;
	@Autowired
	CmsContentAttachmentMapper cmsContentAttachmentMapper;
	@Autowired
	CmsNovelAttachMapper cmsNovelAttachMapper;
	@Autowired
	CmsVideoAttachMapper cmsVideoAttachMapper;
	
	@Autowired
	TaskConfigService taskConfigService;
	
	@Value("${htmlDir}")
	String htmlFolder;
	@Value("${projectName}")
	String projectName;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Logger log = Logger.getLogger(ExportData.class);

	@Scheduled(cron = "1 1 23 * * ?")
 	//@Scheduled(fixedDelay=30000)
	public void export() {
		TaskConfig taskConfig = taskConfigService.get("123");
		if(taskConfig.getIsKeywords()!=null && taskConfig.getIsKeywords());
		String[] keywords = null;
		if(taskConfig.getIsKeywords())
		{
		keywords = taskConfig.getKeywords().split(",");
		}
		String channelIds = taskConfig.getChannelIds();
		Date endTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(endTime);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE,0);
		ca.set(Calendar.SECOND, 0);
		endTime = ca.getTime();
		ca.add(Calendar.DATE, -1);
		Date startTime = ca.getTime();
		log.info(new Date()+"--"+endTime+"--"+startTime);
		log.info("导出所有站点新增数据开始--"+format.format(new Date()));
		String pathStr = getPath();
		/**从数据库中导出数据，转换成json数据**/
		String addFileName = pathStr+"/"+R.FILE_SITE_CHANNEL_ATTA_ADD_NAME+".json";
		String updateFileName = pathStr+"/"+R.FILE_SITE_CHANNEL_ATTA_UPDATE_NAME+".json";
		String addContentFileName =pathStr+"/"+R.FILE_CONTENT_ADD_NAME+".json";
		String updateContentFileName = pathStr+"/"+R.FILE_CONTENT_UPDATE_NAME+".json";
		startTime.setHours(23);startTime.setMinutes(0);startTime.setSeconds(0);
		endTime.setHours(23);endTime.setMinutes(0);endTime.setSeconds(0);
		String addjsonStr = getJsonStr(startTime, endTime, false,channelIds,keywords);
		String updateJsonStr = getJsonStr(startTime,endTime,true,channelIds,keywords);
		String addContentStr = getContentJsonStr(startTime,endTime,false,channelIds,keywords);
		String updateContentStr = getContentJsonStr(startTime,endTime,true,channelIds,keywords);
		/**生成json文件**/
		try {
			FileUtil.createFile(addFileName, addjsonStr.getBytes("utf-8"));
			FileUtil.createFile(updateFileName, updateJsonStr.getBytes("utf-8"));
			FileUtil.createFile(addContentFileName, addContentStr.getBytes("utf-8"));
			FileUtil.createFile(updateContentFileName, updateContentStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		log.info("导出所有站点新增数据结束--"+format.format(new Date()));
	}
	
	/**
	 * 获取 文章 小说 视频 音乐  及各自附件的中间表
	 * @param startTime
	 * @param endTime
	 * @param isUpdate
	 * @return
	 */
	private String getContentJsonStr(Date startTime, Date endTime, boolean isUpdate,String channelIds,String[] keywords) {
		JsonConfig config = new JsonConfig();  
		config.setIgnoreDefaultExcludes(false);     
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
		config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor(JsonDateValueProcessor.TIME_FORMAT));
		Map map = new HashMap();
		this.putContent(startTime, endTime,map,channelIds,isUpdate,keywords);
		this.putVideo(startTime, endTime,map,channelIds,isUpdate,keywords);
		this.putNovel(startTime, endTime,map,channelIds,isUpdate,keywords);
		this.putAudio(startTime, endTime, map, channelIds,isUpdate,keywords);
		JSONObject jsonStr = JSONObject.fromObject(map,config);
		return jsonStr.toString();
	}


	private void putAudio(Date startTime, Date endTime, Map map,String channelIds,
			boolean isUpdate,String[] keywords) {
		CmsAudio entity = new CmsAudio();
		entity.setCreateTime(endTime);
		entity.setUpdateTime(startTime);
		entity.setChannelIds(strToInteger(channelIds.split(",")));
		List<CmsAudio> list = new ArrayList<CmsAudio>();
		if (isUpdate) {
			list = cmsAudioMapper.selectUpdateData(entity);
		} else {
			list = cmsAudioMapper.selectAddData(entity);
		}
		List<CmsAudioAttach> attaList = new ArrayList<CmsAudioAttach>();
		for(CmsAudio audio:list){
			List<CmsAudioAttach> a = this.cmsAutioAttachMapper.selectAttaList(audio.getId());
			attaList.addAll(a);
		}
		map.put(R.CMS_AUDIO_ATTACHMENT, attaList);
		map.put(R.CMS_AUDIO, list);
		
	}


	private void putNovel(Date startTime, Date endTime, Map map,String channelIds,
			boolean isUpdate,String[] keywords) {
		CmsNovel entity = new CmsNovel();
		entity.setCreateTime(endTime);
		entity.setUpdateTime(startTime);
		entity.setChannelIds(strToInteger(channelIds.split(",")));
		List<CmsNovel> list = new ArrayList<CmsNovel>();
		if (isUpdate) {
			list = cmsNovelMapper.selectUpdateData(entity);
		} else {
			list = cmsNovelMapper.selectAddData(entity);
		}
		List<CmsNovelAttach> attaList = new ArrayList<CmsNovelAttach>();
		for(CmsNovel novel:list){
			List<CmsNovelAttach> a = this.cmsNovelAttachMapper.selectAttaList(novel.getId());
			
			attaList.addAll(a);
		}
		map.put(R.CMS_NOVEL_ATTACHMENT, attaList);
		map.put(R.CMS_NOVEL, list);
		
	}


	private void putVideo(Date startTime, Date endTime, Map map,String channelIds,
			boolean isUpdate,String[] keywords) {
		CmsVideo entity = new CmsVideo();
		entity.setCreateTime(endTime);
		entity.setUpdateTime(startTime);
		entity.setChannelIds(strToInteger(channelIds.split(",")));
		entity.setTitleArray(keywords);
		List<CmsVideo> list = new ArrayList<CmsVideo>();
		if (isUpdate) {
			list = cmsVideoMapper.selectUpdateData(entity);
		} else {
			list = cmsVideoMapper.selectAddData(entity);
		}
		List<CmsVideoAttach> attaList = new ArrayList<CmsVideoAttach>();
		for(CmsVideo video:list){
			List<CmsVideoAttach> a = this.cmsVideoAttachMapper.selectAttaList(video.getId());
			attaList.addAll(a);
		}
		map.put(R.CMS_VIDEO_ATTACHMENT, attaList);
		map.put(R.CMS_VIDEO, list);
		
	}

	private void putContent(Date startTime, Date endTime, Map map,String channelIds,
			boolean isUpdate,String[] keywords) {
		CmsContent entity = new CmsContent();
		entity.setCreateTime(endTime);
		entity.setUpdateTime(startTime);
		log.info(endTime+"--"+startTime);
		entity.setChannelIds(strToInteger(channelIds.split(",")));
		entity.setTitleArray(keywords);
		List<CmsContent> list = new ArrayList<CmsContent>();
		if (isUpdate) {
			list = cmsContentMapper.selectUpdateData(entity);
		} else {
			list = cmsContentMapper.selectAddData(entity);
		}
		List<CmsContentAttachment> attaList = new ArrayList<CmsContentAttachment>();
		for(CmsContent content:list){
			List<CmsContentAttachment> a = this.cmsContentAttachmentMapper.selectAttaList(content.getId());
			attaList.addAll(a);
		}
		map.put(R.CMS_CONTENT_ATTACHMENT, attaList);
		map.put(R.CMS_CONTENT, list);
		
	}


	/**
	 * 获取数据
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param isUpdate
	 *            更新还是新增
	 * @return
	 */
	private String getJsonStr(Date startTime, Date endTime, boolean isUpdate,String channelIds,String[] keywords) {
		JsonConfig config = new JsonConfig();  
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
		//config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor(JsonDateValueProcessor.TIME_FORMAT));
		Map map = new HashMap();
		this.putCmsAttachment(startTime, endTime, map,isUpdate);
		this.putCmsAttachmentOther(startTime, endTime, map, isUpdate);
		this.putSite(startTime, endTime, map, isUpdate);
		this.putChannel(startTime, endTime, map, isUpdate);
		this.putUserAcount(startTime,endTime,map,isUpdate);
		JSONObject jsonStr = JSONObject.fromObject(map,config);
		return jsonStr.toString();
	}
	
	private void putUserAcount(Date startTime, Date endTime, Map map,
			boolean isUpdate) {
		UserAccount entity = new UserAccount();
		entity.setCreateTime(startTime);
		entity.setUpdateTime(startTime);
		List<UserAccount> list = new ArrayList<UserAccount>();
		if (isUpdate) {
			list = userAccountMapper.selectUpdateData(entity);
		} else {
			list = userAccountMapper.selectAddData(entity);
		}
		map.put(R.T_USER_ACCOUNT, list);
	}

	/** 频道数据 */
	private void putChannel(Date startTime, Date endTime, Map map,
			boolean isUpdate) {
		CmsChannel entity = new CmsChannel();
		entity.setCreateTime(startTime);
		entity.setUpdateTime(startTime);
		List<CmsChannel> list = new ArrayList<CmsChannel>();
		if (isUpdate) {
			list = cmsChannelMapper.selectUpdateData(entity);
		} else {
			list = cmsChannelMapper.selectAddData(entity);
		}
		map.put(R.CMS_CHANNEL, list);

	}

	/**
	 * 获取新增的站点
	 */
	private void putSite(Date startTime, Date endTime, Map map, boolean isUpdate) {
		CmsSite entity = new CmsSite();
		entity.setCreateTime(startTime);
		entity.setUpdateTime(startTime);
		List<CmsSite> list = new ArrayList<CmsSite>();
		if (isUpdate) {
			list = cmsSiteMapper.selectUpdateData(entity);
		} else {
			list = cmsSiteMapper.selectAddData(entity);
		}
		map.put(R.CMS_SITE, list);

	}

	private void putCmsAttachmentOther(Date startTime, Date endTime, Map map,
			boolean isUpdate) {
		CmsAttachmentOther entity = new CmsAttachmentOther();
		entity.setCreateTime(startTime);
		entity.setUpdateTime(startTime);
		List<CmsAttachmentOther> list = new ArrayList<CmsAttachmentOther>();
		if (isUpdate) {
			list = cmsAttachmentOtherMapper.selectUpdateData(entity);
		} else {
			list = cmsAttachmentOtherMapper.selectAddData(entity);
		}
		map.put(R.CMS_ATTACHMENT_OTHER, list);

	}

	private void putCmsAttachment(Date startTime, Date endTime, Map map,
			boolean isUpdate) {
		CmsAttachment entity = new CmsAttachment();
		entity.setCreateTime(startTime);
		entity.setUpdateTime(startTime);
		List<CmsAttachment> list = new ArrayList<CmsAttachment>();
		if (isUpdate) {
			list = cmsAttachmentMapper.selectUpdateData(entity);
		} else {
			list = cmsAttachmentMapper.selectAddData(entity);
		}
		map.put(R.CMS_ATTACHMENT, list);
	}

	/*private Date getTimeStamp(Date d, String time) {
		Calendar ca = Calendar.getInstance();
		try {
			ca.setTime(time0.parse(time1.format(d) + " " + time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		d = ca.getTime();
		return d;
	}*/
	
	private List<Integer> strToInteger(String[] keywords)
	{
		List<Integer> keyword = new ArrayList<Integer>();
		for(String key :keywords)
		{
			keyword.add(Integer.valueOf(key)); 
		}
		return keyword;
	}
	
	private String getPath() {
		String baseDir = System.getProperty("web.root");
		String path = baseDir.substring(0, baseDir.indexOf(projectName) - 1)
				+ htmlFolder+"/json";
		//String pathStr = path + File.separator + "${}-" + time1.format(now)
				//+ ".json";
		return path;
	}
}
