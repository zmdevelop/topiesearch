package com.dm.cms.controller;

import com.dm.cms.model.CmsChannel;
import com.dm.cms.model.CmsContent;
import com.dm.cms.service.CmsChannelService;
import com.dm.cms.service.CmsContentService;
import com.dm.cms.service.CmsSiteService;
import com.dm.platform.util.PageConvertUtil;
import com.dm.platform.util.ResponseUtil;
import com.dm.platform.util.SqlParam;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cgj on 2015/11/29.
 */
@Controller
@RequestMapping("/cms/content")
public class CmsContentController {
	@Autowired
	CmsContentService cmsContentService;
	@Autowired
	CmsChannelService cmsChannelService;
	
	@Autowired
	CmsSiteService cmsSiteService;

	@RequestMapping("/page")
	public String page(Model model) {
		return "/cms/content/page";
	}

	@RequestMapping("/shpage")
	public String shpage(Model model) {
		return "/cms/content/shpage";
	}

	@RequestMapping("/list")
	public @ResponseBody
	Object list(
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			CmsContent cmsContent,
			@RequestParam(value = "sort", required = false) String sort) {
		if (cmsContent.getChannelId() == null)
			return PageConvertUtil.emptyGrid();
		Map map = new SqlParam<CmsContent>().autoParam(cmsContent, sort);
		map.put("model", cmsContent);
		PageInfo<CmsContent> page = cmsContentService.findCmsContentByPage(
				pageNum, pageSize, map);
		return PageConvertUtil.grid(page);
	}

	@RequestMapping("/load")
	public @ResponseBody
	Object load(
			@RequestParam(value = "contentId", required = true) Integer contentId) {
		CmsContent cmsContent = cmsContentService.findOneById(contentId);
		cmsContent.setAttachmentIds(cmsContentService
				.findCmsAttachementIdsByCmsContentId(contentId));
		return cmsContent;
	}

	@RequestMapping("/insertOrUpdate")
	public @ResponseBody
	Object insertOrUpdate(
			CmsContent cmsContent,
			@RequestParam(value = "attachmentIds", required = false) String attachmentIds) {
		if (cmsContent.getId() == null) {
			insert(cmsContent);
			cmsContentService.insertAttachment(cmsContent.getId(),
					attachmentIds);
		} else {
			update(cmsContent);
			cmsContentService.updateAttachment(cmsContent.getId(),
					attachmentIds);
		}

		return ResponseUtil.success("操作成功");
	}

	private void insert(CmsContent cmsContent) {
		CmsChannel cmsChannel = cmsChannelService.findOneById(cmsContent
				.getChannelId());
		cmsContent.setSiteDomain(cmsChannel.getSiteDomain());
		cmsContent.setChannelEnName(cmsChannel.getEnName());
		cmsContentService.insertCmsContent(cmsContent);
	}

	private void update(CmsContent cmsContent) {
		CmsChannel cmsChannel = cmsChannelService.findOneById(cmsContent
				.getChannelId());
		cmsContent.setSiteDomain(cmsChannel.getSiteDomain());
		cmsContent.setChannelEnName(cmsChannel.getEnName());
		cmsContent.setStatus(new Short("0"));
		cmsContentService.updateCmsContent(cmsContent);
	}

	@RequestMapping("/delete")
	public @ResponseBody
	Object delete(
			@RequestParam(value = "contentId", required = true) String contentId,HttpServletRequest request) {
		if (StringUtils.isBlank(contentId)) {
			return ResponseUtil.success("请选择要删除的项！");
		}
			String[] id = contentId.split(",");
			CmsContent content = cmsContentService.findOneById(Integer.valueOf(Integer.valueOf(id[0])));
			for (String primaryKey : id) {
				cmsContentService.deleteCmsContentById(Integer
						.valueOf(primaryKey));
			}
			CmsChannel cmsChannel = cmsChannelService.findOneById(content.getChannelId());
			cmsSiteService.generatorHtml(cmsChannel.getSiteId(), request);
			return ResponseUtil.success("操作成功");
	}

	@RequestMapping("/commit")
	@ResponseBody
	public Object commit(
			HttpServletRequest request,
			@RequestParam(value = "contentId", required = true)String contentId) {
		if(StringUtils.isBlank(contentId)){
			return ResponseUtil.error("请选择要提交的内容");
		}
		String[] id = contentId.split(",");
		int count = 0;
		for(String contid:id){
			Integer contentid=Integer.valueOf(contid);
			boolean succ=this.cmsContentService.updateContentState(request, contentid,
					new Short("1"));
			if(succ){
				count++;
			}
		}
		return ResponseUtil.success("成功提交"+count+"条");
	}

	@RequestMapping("/check")
	@ResponseBody
	public Object publish(
			HttpServletRequest request,
			@RequestParam(value = "ids", required = true)String contentId,
			@RequestParam(value = "status", required = true) short status) {
		if(StringUtils.isBlank(contentId)){
			return ResponseUtil.error("请选择要通过的内容");
		}
		String[] id = contentId.split(",");
		int count = 0;
		for(String contid:id){
			Integer contentid = Integer.valueOf(contid);
			boolean succ = this.cmsContentService.updateContentState(request,
					contentid, status);
			if(succ){
				count++;
			}
		}
		String result="";
		if(status==2)
		{
			result="成功发布"+count+"条!";
		}
		else
		{
			result="已经驳回"+count+"条";
		}
		return ResponseUtil.success(result);
	}

	@RequestMapping("/back")
	@ResponseBody
	public Object cancelStaticHtml(
			HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String contentId) {
		if(StringUtils.isBlank(contentId)){
			return ResponseUtil.error("请选择要驳回的内容");
		}
		String[] id = contentId.split(",");
		int count = 0;
		for(String contid:id){
			Integer contentid = Integer.valueOf(contid);
			boolean succ = this.cmsContentService.updateContentState(request, contentid,
					new Short("3"));
			if(succ){
				count++;
			}
		}
		return ResponseUtil.success("驳回"+count+"条!");
	}

	@RequestMapping("sort")
	@ResponseBody
	public Object sort(
			@RequestParam(value = "id", required = true) Integer contentId,
			@RequestParam(value = "seq", required = true) Integer seq) {
		this.cmsContentService.sort(contentId, seq);
		return ResponseUtil.success("操作成功");
	}

	@RequestMapping("/cutTo")
	public @ResponseBody
	Object cutTo(
			@RequestParam(value = "ids", required = true) String contentId,
			Long channelId) {
		try {
			if (contentId == null || contentId.trim().equals("")) {
				return ResponseUtil.error("请选择要移动的内容！");
			}
			if(channelId==null){
				return ResponseUtil.error("请选择频道！");
			}
			String[] id = contentId.split(",");
			for (int i=id.length-1;i>=0;i--) {
				String contentid = id[i];
				CmsContent content = this.cmsContentService.findOneById(Integer
						.valueOf(contentid));
				if (content.getChannelId().intValue() == channelId.intValue()) {
					continue;
				}
				content.setChannelId(channelId.intValue());
				content.setStatus(new Short("1"));
				cmsContentService.updateCmsContent(content);
			}
			return ResponseUtil.success("移动成功！");
		} catch (Exception e) {
			return ResponseUtil.error("移动失败！");
		}
	}

	/**
	 * 复制到某频道
	 * 
	 * @param contentId
	 * @param channelId
	 * @return
	 */
	@RequestMapping("/copyTo")
	public @ResponseBody
	Object copyTo(
			@RequestParam(value = "ids", required = true) String contentId,
			String channelId) {
		if (channelId == null || channelId.equals("")) {
			return ResponseUtil.error("请选择频道！");
		}
		if (contentId == null || contentId.equals("")) {
			return ResponseUtil.error("请选择要复制的内容！");
		}
		String[] contentids = contentId.split(",");
		for (int i=contentids.length-1;i>=0;i--) {
			String contId = contentids[i];
			CmsContent content = this.cmsContentService.findOneById(Integer
					.valueOf(contId));
			String[] channelids = channelId.split(",");
			for (String cId : channelids) {
				try {
					if (content.getChannelId().intValue() == Integer.valueOf(
							cId).intValue()) {
						continue;
					} else {
						CmsContent ce = new CmsContent();
						BeanUtils.copyProperties(content, ce);
						ce.setChannelId(Integer.valueOf(cId));
						ce.setStatus(new Short("1"));
						ce.setId(null);
						this.cmsContentService.insertCmsContent(ce);
						String s = cmsContentService.findCmsAttachementIdsByCmsContentId(Integer
								.valueOf(contId));
						this.cmsContentService.insertAttachment(ce.getId(), s);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseUtil.error("复制失败！");
				}
			}
		}
		return ResponseUtil.success("复制成功！");
	}
}
