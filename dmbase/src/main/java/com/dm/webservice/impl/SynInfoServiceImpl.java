package com.dm.webservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.dm.platform.model.UserAccount;
import com.dm.platform.service.UserAccountService;
import com.dm.webservice.SynInfoService;

public class SynInfoServiceImpl implements SynInfoService{

	@Resource
	UserAccountService userAccountService;
	
	@Override
	public String getUserInfo(String userId) {
		UserAccount user =  userAccountService.findOne(userId);
		JsonConfig jConfig = new JsonConfig();
		jConfig.setExcludes(new String[] { "roles", "org", "remoteIpAddr",
				"headphoto", "authorities" });
		return JSONObject.fromObject(user,jConfig).toString();
	}
}
