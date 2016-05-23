package com.dm.task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dm.platform.model.UserAccount;
import com.dm.platform.service.UserAccountService;

//@Component
public class ChannelUserInit implements InitializingBean {

	private Logger log = LoggerFactory.getLogger(ChannelUserInit.class);
	
	@Autowired
	UserAccountService userAccountService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		log.info("======开始初始化频道和用户信息到缓存=======");
		List<UserAccount> users = userAccountService.listAllUser();
		ObjectSerilaze os = new ObjectSerilaze();
		for(UserAccount user:users)
		{
			if(RedisUtil.getJedis()!=null)
			RedisUtil.getJedis().set(user.getCode().getBytes(), os.serialize(user));
		}
	}
}
