package com.dm.task.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
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

@Component
public class ImportData {
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
	@Value("${htmlDir}")
	String htmlFolder;
	@Value("${projectName}")
	String projectName;

	private Logger log = Logger.getLogger(ImportData.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//@Scheduled(fixedDelay=50000)
	@Scheduled(cron = "1 59 23 * * ?")
	public void importdata() {
		log.info("导入所有站点新增数据开始-"+format.format(new Date()));
		/***读取json文件数据**/
		String pathStr = getPath();
		String addFileName = pathStr+"/"+R.FILE_SITE_CHANNEL_ATTA_ADD_NAME+".json";
		String updateFileName = pathStr+"/"+R.FILE_SITE_CHANNEL_ATTA_UPDATE_NAME+".json";
		String addContentFileName =pathStr+"/"+R.FILE_CONTENT_ADD_NAME+".json";
		String updateContentFileName = pathStr+"/"+R.FILE_CONTENT_UPDATE_NAME+".json";
		String addJsonStr = getJsonStr(addFileName);
		String updateJsonStr = getJsonStr(updateFileName);
		String addContentStr = getJsonStr(addContentFileName);
		String updateContentStr = getJsonStr(updateContentFileName);
		/**json数据转化为实体，导入到数据库中***/
		this.updateOrInsertSiteChannelUserAccountAttachment(addJsonStr, false);
		this.updateOrInsertSiteChannelUserAccountAttachment(updateJsonStr, true);
		this.updateOrInsertContentVideoNovelAudio(addContentStr, false);
		this.updateOrInsertContentVideoNovelAudio(updateContentStr, true);
		log.info("导入所有站点新增数据结束-"+format.format(new Date()));
	}

	private void updateOrInsertContentVideoNovelAudio(
			String updateOrInsertContentStr, boolean isUpdate) {
		String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		JSONObject map = JSONObject.fromObject(updateOrInsertContentStr);
		this.updateOrInsertContentAndContentAttachment(map, isUpdate);
		this.updateOrInsertVideoAndVideoAttachment(map, isUpdate);
		this.updateOrInsertNovelAndNovelAttachment(map, isUpdate);
		this.updateOrInsertAudioAndAudioAttachment(map, isUpdate);
	}

	private void updateOrInsertAudioAndAudioAttachment(JSONObject map,
			boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_AUDIO);
		int size = jsonArray.size();
		if (isUpdate) {
			log.debug("audio 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAudio entity = (CmsAudio) JSONObject.toBean(c,
						CmsAudio.class);
				log.info("audio----------"+entity.getCreateTime()+"--"+entity.getUpdateTime());
				this.cmsAudioMapper.updateByPrimaryKey(entity);
				this.cmsAutioAttachMapper.deleteAttaByContentId(entity.getId());
			}
		} else {
			log.debug("audio 新增数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAudio entity = (CmsAudio) JSONObject.toBean(c,
						CmsAudio.class);
				this.cmsAudioMapper.insert(entity);
			}
		}
		JSONArray jsonArray_attr = (JSONArray) map.get(R.CMS_AUDIO_ATTACHMENT);
		int size_atta = jsonArray_attr.size();
		for (int i = 0; i < size_atta; i++) {
			JSONObject c = jsonArray_attr.getJSONObject(i);
			CmsAudioAttach entity = (CmsAudioAttach) JSONObject.toBean(c,
					CmsAudioAttach.class);
			this.cmsAutioAttachMapper.insert(entity);
		}

	}

	private void updateOrInsertNovelAndNovelAttachment(JSONObject map,
			boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_NOVEL);
		int size = jsonArray.size();
		if (isUpdate) {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsNovel entity = (CmsNovel) JSONObject.toBean(c,
						CmsNovel.class);
				this.cmsNovelMapper.updateByPrimaryKey(entity);
				this.cmsNovelAttachMapper.deleteAttaByContentId(entity.getId());
			}
		} else {
			log.debug("novel 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsNovel entity = (CmsNovel) JSONObject.toBean(c,
						CmsNovel.class);
				this.cmsNovelMapper.insert(entity);
			}
		}
		JSONArray jsonArray_attr = (JSONArray) map.get(R.CMS_NOVEL_ATTACHMENT);
		int size_atta = jsonArray_attr.size();
		for (int i = 0; i < size_atta; i++) {
			JSONObject c = jsonArray_attr.getJSONObject(i);
			CmsNovelAttach entity = (CmsNovelAttach) JSONObject.toBean(c,
					CmsNovelAttach.class);
			this.cmsNovelAttachMapper.insert(entity);
		}

	}

	private void updateOrInsertVideoAndVideoAttachment(JSONObject map,
			boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_VIDEO);
		int size = jsonArray.size();
		if (isUpdate) {
			log.debug("video 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsVideo entity = (CmsVideo) JSONObject.toBean(c,
						CmsVideo.class);
				this.cmsVideoMapper.updateByPrimaryKey(entity);
				this.cmsVideoAttachMapper.deleteAttaByContentId(entity.getId());
			}
		} else {
			log.debug("video 新增数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsVideo entity = (CmsVideo) JSONObject.toBean(c,
						CmsVideo.class);
				this.cmsVideoMapper.insert(entity);
			}
		}
		JSONArray jsonArray_attr = (JSONArray) map.get(R.CMS_VIDEO_ATTACHMENT);
		int size_atta = jsonArray_attr.size();
		for (int i = 0; i < size_atta; i++) {
			JSONObject c = jsonArray_attr.getJSONObject(i);
			CmsVideoAttach entity = (CmsVideoAttach) JSONObject.toBean(c,
					CmsVideoAttach.class);
			this.cmsVideoAttachMapper.insertSelective(entity);
		}

	}

	private void updateOrInsertContentAndContentAttachment(JSONObject map,
			boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_CONTENT);
		int size = jsonArray.size();
		if (isUpdate) {
			log.debug("content 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsContent entity = (CmsContent) JSONObject.toBean(c,
						CmsContent.class);
				this.cmsContentMapper.updateByPrimaryKeyWithBLOBs(entity);
				this.cmsContentAttachmentMapper.deleteAttaByContentId(entity
						.getId());
			}
		} else {
			log.debug("content 新增数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsContent entity = (CmsContent) JSONObject.toBean(c,
						CmsContent.class);
				this.cmsContentMapper.insert(entity);
			}
		}
		JSONArray jsonArray_attr = (JSONArray) map
				.get(R.CMS_CONTENT_ATTACHMENT);
		int size_atta = jsonArray_attr.size();
		for (int i = 0; i < size_atta; i++) {
			JSONObject c = jsonArray_attr.getJSONObject(i);
			CmsContentAttachment entity = (CmsContentAttachment) JSONObject
					.toBean(c, CmsContentAttachment.class);
			this.cmsContentAttachmentMapper.insertSelective(entity);
		}

	}

	private void updateOrInsertSiteChannelUserAccountAttachment(
			String updateOrInsertJsonStr, boolean isUpdate) {
		String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		JSONObject map = JSONObject.fromObject(updateOrInsertJsonStr);
		this.updateOrInsertCmsSite(map, isUpdate);
		this.updateOrInsertCmsChannel(map, isUpdate);
		this.updateOrInsertCmsUserAccount(map, isUpdate);
		this.updateOrInsertCmsAttachment(map, isUpdate);
		this.updateOrInsertCmsAttachmentOther(map, isUpdate);
	}

	private void updateOrInsertCmsAttachment(JSONObject map, boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_ATTACHMENT);
		int size = jsonArray.size();
		if (isUpdate) {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAttachment entity = (CmsAttachment) JSONObject.toBean(c,
						CmsAttachment.class);
				this.cmsAttachmentMapper.updateByPrimaryKey(entity);
			}
		} else {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAttachment entity = (CmsAttachment) JSONObject.toBean(c,
						CmsAttachment.class);
				this.cmsAttachmentMapper.insert(entity);
			}
		}
	}

	private void updateOrInsertCmsAttachmentOther(JSONObject map,
			boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_ATTACHMENT_OTHER);
		int size = jsonArray.size();
		if (isUpdate) {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAttachmentOther entity = (CmsAttachmentOther) JSONObject
						.toBean(c, CmsAttachmentOther.class);
				this.cmsAttachmentOtherMapper.updateByPrimaryKey(entity);
			}
		} else {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsAttachmentOther entity = (CmsAttachmentOther) JSONObject
						.toBean(c, CmsAttachmentOther.class);
				this.cmsAttachmentOtherMapper.insert(entity);
			}
		}

	}

	private void updateOrInsertCmsUserAccount(JSONObject map, boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.T_USER_ACCOUNT);
		int size = jsonArray.size();
		if (isUpdate) {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				UserAccount entity = (UserAccount) JSONObject.toBean(c,
						UserAccount.class);
				entity.setOrgId(null);
				entity.setHeadphotoId(null);
				this.userAccountMapper.updateByPrimaryKey(entity);
			}
		} else {
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				UserAccount entity = (UserAccount) JSONObject.toBean(c,
						UserAccount.class);
				entity.setOrgId(null);
				entity.setHeadphotoId(null);
				this.userAccountMapper.insert(entity);
			}
		}

	}

	private void updateOrInsertCmsChannel(JSONObject map, boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_CHANNEL);
		int size = jsonArray.size();
		if (isUpdate) {
			log.debug("channel 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsChannel entity = (CmsChannel) JSONObject.toBean(c,
						CmsChannel.class);
				this.cmsChannelMapper.updateByPrimaryKey(entity);
			}
		} else {
			log.debug("channel 新增数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsChannel entity = (CmsChannel) JSONObject.toBean(c,
						CmsChannel.class);
				this.cmsChannelMapper.insert(entity);
			}
		}
	}

	private void updateOrInsertCmsSite(JSONObject map, boolean isUpdate) {
		JSONArray jsonArray = (JSONArray) map.get(R.CMS_SITE);
		int size = jsonArray.size();
		if (isUpdate) {
			log.debug("site 更新数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsSite entity = (CmsSite) JSONObject.toBean(c, CmsSite.class);
				this.cmsSiteMapper.updateByPrimaryKey(entity);
			}
		} else {
			log.debug("site 新增数据 " + size + "条！");
			for (int i = 0; i < size; i++) {
				JSONObject c = jsonArray.getJSONObject(i);
				CmsSite entity = (CmsSite) JSONObject.toBean(c, CmsSite.class);
				this.cmsSiteMapper.insert(entity);
			}
		}
	}

	private String getJsonStr(String fileName) {
		String jsonStr = "";
		try {
			File file = new File(fileName);
			if (file.exists()) {
				jsonStr = new String(FileUtil.getBytesFromPath(fileName),
						"utf-8");
			} else {
				log.error("导入数据文件不存在：" + fileName);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("读取导入数据文件出错" + e.getMessage());
		}
		return jsonStr;
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

	private String getPath() {
		String baseDir = System.getProperty("web.root");
		String path = baseDir.substring(0, baseDir.indexOf(projectName) - 1)
				+ htmlFolder+"/json";
		//String pathStr = path + File.separator + "${}-" + time1.format(now)
				//+ ".json";
		return path;
	}
}
