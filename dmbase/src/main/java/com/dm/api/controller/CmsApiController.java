package com.dm.api.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dm.cms.model.CmsComment;
import com.dm.cms.service.CmsCommentService;
import com.dm.platform.dao.CommonDAO;
import com.dm.platform.model.UserAccount;
import com.dm.platform.model.UserAttrEntity;
import com.dm.platform.service.UserAccountService;
import com.dm.platform.service.UserAttrService;
import com.dm.platform.util.ResponseUtil;
import com.dm.platform.util.SimpleCryptoUtil;
import com.dm.platform.util.UUIDUtils;
import com.dm.platform.util.UserAccountUtil;
import com.dm.task.service.ObjectSerilaze;
import com.dm.task.service.RedisUtil;
import com.dm.webservice.SynInfoService;
import com.dm.webservice.SynVisitService;
import com.dm.webservice.impl.SynVisitServiceImpl;
import com.github.pagehelper.PageInfo;

/**
 * Created by cgj on 2015/12/5.
 */
@Controller @RequestMapping("/api") public class CmsApiController {
    @Autowired CmsCommentService commentService;
    @Autowired CommonDAO commonDAO;
    @Resource UserAccountService userAccountService;
    @Resource UserAttrService userAttrService;
    @Resource SynInfoService synInfoService;
    @Resource SynVisitService synVisitService;
    
    private Logger log = LoggerFactory.getLogger(CmsApiController.class);
    private static final int EXPIRE_TIME = 60*60*24*7;

    @Value("${htmlDir}")
    String htmlFolder;
    @Value("${seed}")
    String seed;
    
    

    /**
     * @param contentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/cms/comment", method = RequestMethod.GET) @ResponseBody
    public Object listComment(@RequestParam(value = "contentId", required = true) Integer contentId,
        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo =
            commentService.findCmsCommentByCmsContentIdByPage(pageNum, pageSize, contentId);
        Map map = ResponseUtil.success();
        map.put("pageInfo", pageInfo);
        return map;
    }
    
    @RequestMapping(value = "/cms/postComment") @ResponseBody
    public Map comment(CmsComment cmsComment,String callback) {
        UserAccount userAccount = UserAccountUtil.getInstance().getCurrentUserAccount();
        if (userAccount == null) {
        	cmsComment.setCommentUserName("anonymous");
        }
        else{
        cmsComment.setCommentUserLoginName(userAccount.getLoginname());
        cmsComment.setCommentUserName(userAccount.getName());
        cmsComment.setCommentUserAvatar(userAccount.getHeadpic());
        }
        commentService.insertCmsComment(cmsComment);
        Map map = new HashMap();
        map.put("msg","评论成功！");
        return map;
    }
    
    @RequestMapping("/cms/comment/list")
    @ResponseBody
    public PageInfo<CmsComment> getCommetnList(Integer pageNum,Integer pageSize, @RequestParam(value = "contentId",required=true)
    Integer contentId)
    {
    	pageNum = pageNum==null?1:pageNum;
    	pageSize = pageSize==null?6:pageSize;
    	PageInfo<CmsComment> pageInfo = commentService.findCmsCommentByCmsContentIdByPage(pageNum, pageSize, contentId);
    	return pageInfo;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUserAccount") 
    @ResponseBody
    public Map getCurrentUserAccount() {
        UserAccount userAccount = UserAccountUtil.getInstance().getCurrentUserAccount();
        Map map = new HashMap();
        if(userAccount==null)
        {
        	map.put("status",0);
        	map.put("msg", "未登录！");
        	return map;
        }
        map.put("status", 1);
        map.put("userId",userAccount.getCode());
    	map.put("userName",userAccount.getUsername());
    	map.put("loginName", userAccount.getLoginname());
        return map;
    }
    
	@RequestMapping(value = "/register") 
	@ResponseBody
    public Map register(UserAccount userAccount,String sex) {
		String userId = UUIDUtils.getUUID16();
		userAccount.setCode(userId);
		UserAttrEntity userAttr = new UserAttrEntity();
		userAttr.setUserId(userId);
		userAttr.setGender(sex);
		userAccount.setOrg(null);
		userAccount.setNonLocked(true);
		userAccount.setEnabled(true);
		try {
			userAccount.setSynPassword(SimpleCryptoUtil.encrypt(seed, userAccount.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		sha.setEncodeHashAsBase64(false);
		userAccount.setPassword(sha.encodePassword(
				userAccount.getPassword(), null));
	    userAccountService.insertUser(userAccount);
	    userAttrService.insert(userAttr);
	    synVisitService.putInfo(userId, "11");
        return ResponseUtil.success("注册成功！");
    }
	
	@RequestMapping(value = "/checkEmail") 
	@ResponseBody
    public Map checkEmail(String email) {
		boolean flag = userAccountService.checkEmail(email);
        if(flag)
        {
        	return ResponseUtil.error();
        }
       return ResponseUtil.success();
    }
	
	@RequestMapping("/checkValidateCode")
	@ResponseBody
	public Map checkValidateCode(HttpServletRequest request,String validateCode) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("validateCode");
		if(obj!=null){
		String sessionValidateCode = obtainSessionValidateCode(session);
		session.setAttribute("validateCode", null);
		if (StringUtils.isEmpty(validateCode)
				|| !sessionValidateCode.equalsIgnoreCase(validateCode)) {
			return ResponseUtil.error("验证码错误");
		}
		}
		return ResponseUtil.success();
	}
	
	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute("validateCode");
		return null == obj ? "" : obj.toString();
	}
    
}
