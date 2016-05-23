package com.dm.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dm.cms.model.CmsAttachment;
import com.dm.cms.model.CmsAudio;
import com.dm.cms.model.CmsChannel;
import com.dm.cms.model.CmsContent;
import com.dm.cms.model.CmsNovel;
import com.dm.cms.model.CmsSite;
import com.dm.cms.model.CmsTemplate;
import com.dm.cms.model.CmsVideo;
import com.dm.cms.service.CmsAttachmentService;
import com.dm.cms.service.CmsAudioService;
import com.dm.cms.service.CmsChannelService;
import com.dm.cms.service.CmsContentService;
import com.dm.cms.service.CmsNovelService;
import com.dm.cms.service.CmsSiteService;
import com.dm.cms.service.CmsTemplateService;
import com.dm.cms.service.CmsVideoService;

/**
 * Created by cgj on 2015/11/23.
 */
@Controller
@RequestMapping("/portal")
public class CmsPortalController {

	@Value("${projectName}")
	String projectName;
	
	@Value("${htmlMobileDir}")
	String htmlMobileFolder;
	
	@Value("${htmlDir}")
	String htmlFolder;
	@Value("${template.mobileBasePath}")
	String mobileFolder;
	@Value("${template.basePath}")
	String templateFolder;

	@Autowired
	protected CmsVideoService cmsVideoService;
	@Autowired
	protected CmsAudioService cmsAudioService;
	@Autowired
	protected CmsSiteService cmsSiteService;
	@Autowired
	protected CmsChannelService cmsChannelService;
	@Autowired
	protected CmsContentService cmsContentService;
	@Autowired
	protected CmsTemplateService cmsTemplateService;
	@Autowired
	protected CmsAttachmentService cmsAttachmentService;
	@Autowired
	protected CmsNovelService cmsNovelService;

	private Logger log = LoggerFactory.getLogger(CmsPortalController.class);

	@RequestMapping("/{domain}/index.htm")
	public String site(Model model, @PathVariable("domain") String domain,
			Device device) {
		CmsSite cmsSite = cmsSiteService.findOneByDomain(domain);
		if (cmsSite == null) {
			return "404";
		}
		model.addAttribute("site", cmsSite);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		model.addAttribute("htmlFolder", htmlFolder);
		return getTemplatePath(cmsSite.getTemplateId(), device.isMobile());

	}

	@RequestMapping("/search.htm")
	public String seach(Model model, Device device,String text) {
		CmsSite cmsSite = cmsSiteService.findOneByDomain("lanhai");
		if (cmsSite == null) {
			return "404";
		}
		model.addAttribute("site", cmsSite);
		model.addAttribute("own", 0);
		if(text==null)
		{
			text="";
		}
		model.addAttribute("text", text);
		model.addAttribute("htmlFolder", htmlFolder);
		if (device.isMobile()) {
			return mobileFolder + "/seach_page";
		}
		return templateFolder + "/seach_page";

	}

	@RequestMapping("/channel/{enName}/{channelId}_{pageNum}.htm")
	public String channel(Model model, Device device,
			@PathVariable("channelId") Integer channelId,
			@PathVariable("enName") String enName,
			@PathVariable(value = "pageNum") Integer pageNum) {
		CmsChannel cmsChannel = cmsChannelService.findOneById(channelId);
		CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
		model.addAttribute("own", channelId);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("site", cmsSite);
		model.addAttribute("channel", cmsChannel);
		model.addAttribute("htmlFolder", htmlFolder);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		model.addAttribute("projectName", projectName);
		if (cmsChannel == null)
			return "404";
		return getTemplatePath(cmsChannel.getTemplateId(), device.isMobile());
	}

	@RequestMapping("/content/{contentId}.htm")
	public String content(Model model, Device device,
			@PathVariable("contentId") Integer contentId,
			RedirectAttributes arrt) {
		CmsContent cmsContent = cmsContentService.findOneById(contentId);
		CmsChannel cmsChannel = cmsChannelService.findOneById(cmsContent
				.getChannelId());
		CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
		model.addAttribute("cmsContent", cmsContent);
		model.addAttribute("projectName", projectName);
		model.addAttribute("site", cmsSite);
		model.addAttribute("own", contentId);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		model.addAttribute("htmlFolder", htmlFolder);
		List<CmsAttachment> cmsAttachmentList = cmsContentService
				.findCmsAttachmentByCmsContentId(contentId);
		if (cmsAttachmentList.size() > 0) {
			model.addAttribute("cmsAttachments", cmsAttachmentList);
		}
		if (cmsContent == null)
			return "404";
		// return "/template/content";
		return getTemplatePath(cmsContent.getTemplateId(), device.isMobile());
	}

	@RequestMapping("/content_video/{videoId}.htm")
	public String videoShow(Model model, Device device,
			@PathVariable("videoId") Integer videoId) {
		model.addAttribute("projectName", projectName);
		model.addAttribute("htmlFolder", htmlFolder);
		CmsVideo cmsVideo = cmsVideoService.findOne(videoId);
		CmsChannel cmsChannel = cmsChannelService.findOneById(cmsVideo
				.getChannelId());
		CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
		model.addAttribute("cmsVideo", cmsVideo);
		model.addAttribute("own", cmsVideo.getId());
		model.addAttribute("site", cmsSite);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		return getTemplatePath(cmsVideo.getTemplateId(), device.isMobile());
	}

	@RequestMapping("/content_audio/{audioId}.htm")
	public String audioShow(Model model, Device device,
			@PathVariable("audioId") Integer audioId) {
		model.addAttribute("projectName", projectName);
		model.addAttribute("htmlFolder", htmlFolder);
		CmsAudio cmsAudio = cmsAudioService.findOne(audioId);
		CmsChannel cmsChannel = cmsChannelService.findOneById(cmsAudio
				.getChannelId());
		CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
		model.addAttribute("cmsAudio", cmsAudio);
		model.addAttribute("own", cmsAudio.getId());
		model.addAttribute("site", cmsSite);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		return getTemplatePath(cmsAudio.getTemplateId(), device.isMobile());
	}

	@RequestMapping("/content_novel/{novelId}.htm")
	public String novel(Model model, Device device,
			@PathVariable("novelId") Integer novelId, RedirectAttributes arrt) {
		CmsNovel novel = this.cmsNovelService.findById(novelId);
		if (novel == null)
			return "404";
		model.addAttribute("cmsNovel", novel);
		model.addAttribute("projectName", projectName);
		model.addAttribute("own", novelId);
		CmsChannel cmsChannel = cmsChannelService.findOneById(novel
				.getChannelId());
		CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
		model.addAttribute("htmlFolder", htmlFolder);
		model.addAttribute("site", cmsSite);
		model.addAttribute("htmlMobileFolder", htmlMobileFolder);
		return getTemplatePath(novel.getTemplateId(), device.isMobile());
	}

	private String getTemplatePath(Integer tempateId, boolean isMobile) {
		CmsTemplate cmsTemplate = cmsTemplateService.findOneById(tempateId);
		String template = cmsTemplate.getTemplatePath().replace(".ftl", "");
		if (isMobile) {
			template = templateFolder+template.replace(templateFolder, mobileFolder);
		}
		return template;
	}

	/*
	 * @RequestMapping("/channel/{enName}_{channelId}.htm") public String
	 * channel(Model model, @PathVariable("channelId") Integer channelId,
	 * 
	 * @PathVariable("enName") String enName,RedirectAttributes arrt ) {
	 * CmsChannel cmsChannel = cmsChannelService.findOneById(channelId);
	 * arrt.addFlashAttribute("cmsChannel", cmsChannel); CmsSite cmsSite =
	 * cmsSiteService.findOneById(cmsChannel.getSiteId()); String str =
	 * "/"+cmsSite.getDomain()+"/"+enName; String redirectUrl =
	 * "redirect:/portal"+str+".htm"; log.debug("重定向地址！{}",redirectUrl); if
	 * (cmsChannel == null) return "404"; return redirectUrl; }
	 * 
	 * @RequestMapping("/content/{contentId}.htm") public String content(Model
	 * model, @PathVariable("contentId") Integer contentId, RedirectAttributes
	 * arrt ) { model.addAttribute("basePath", getWholePath()); CmsContent
	 * cmsContent = cmsContentService.findOneById(contentId); CmsChannel
	 * cmsChannel = cmsChannelService.findOneById(cmsContent.getChannelId());
	 * CmsSite cmsSite = cmsSiteService.findOneById(cmsChannel.getSiteId());
	 * arrt.addFlashAttribute("cmsContent", cmsContent); String str =
	 * "/"+cmsSite.getDomain()+"/"+cmsChannel.getEnName()+"/"+contentId; String
	 * redirectUrl = "redirect:/portal"+str+".htm";
	 * log.debug("重定向地址！{}",redirectUrl); if (cmsContent == null) return "404";
	 * //return "/template/content"; return redirectUrl; }
	 * 
	 * @RequestMapping("/{domain}/{enName}") public String channel(Model model,
	 * @PathVariable("domain") String domain,
	 * 
	 * @PathVariable("enName") String enName) { model.addAttribute("basePath",
	 * getWholePath()); CmsChannel cmsChannel =
	 * cmsChannelService.findOneByPortal(domain, enName); if (cmsChannel ==
	 * null) return "404"; return channelUrl(cmsChannel);
	 * 
	 * }
	 * 
	 * @RequestMapping("/{domain}/{enName}/{contentId}") public String
	 * content(Model model, @PathVariable("domain") String domain,
	 * 
	 * @PathVariable("enName") String enName, @PathVariable("contentId") Integer
	 * contentId) { model.addAttribute("basePath", getWholePath()); CmsContent
	 * cmsContent = cmsContentService.findOneByPortal(domain, enName,
	 * contentId); model.addAttribute("cmsContent", cmsContent);
	 * List<CmsAttachment> cmsAttachmentList =
	 * cmsContentService.findCmsAttachmentByCmsContentId(contentId);
	 * model.addAttribute("cmsAttachments", cmsAttachmentList); if (cmsContent
	 * == null) return "404"; //return "/template/content"; return
	 * contentUrl(cmsContent); }
	 */

	/*
	 * @RequestMapping("/{domain}/{enName}.htm") public String
	 * channelRedirect(Model model,CmsChannel cmsChannel,
	 * @PathVariable("domain") String domain,@PathVariable("enName") String
	 * enName) { model.addAttribute("basePath", getWholePath()); //CmsChannel
	 * channel = cmsChannelService.findOneByPortal(domain, enName); if
	 * (cmsChannel == null) { return "404"; } model.addAttribute("basePath",
	 * getWholePath()); model.addAttribute("ipAddress", ipAddress);
	 * log.debug("ip地址{}",ipAddress); model.addAttribute("channel", cmsChannel);
	 * return getTemplatePath(cmsChannel.getTemplateId());
	 * 
	 * }
	 * 
	 * @RequestMapping("/{domain}/{enName}/{contentId}.htm") public String
	 * contentRedirect(Model model,CmsContent cmsContent,
	 * @PathVariable("domain") String domain,@PathVariable("enName") String
	 * enName) { model.addAttribute("basePath", getWholePath()); //CmsChannel
	 * channel = cmsChannelService.findOneByPortal(domain, enName); if
	 * (cmsContent == null) { return "404"; } model.addAttribute("basePath",
	 * getWholePath()); model.addAttribute("ipAddress", ipAddress);
	 * log.debug("ip地址{}",ipAddress); model.addAttribute("cmsContent",
	 * cmsContent); return getTemplatePath(cmsContent.getTemplateId()); }
	 */
}
