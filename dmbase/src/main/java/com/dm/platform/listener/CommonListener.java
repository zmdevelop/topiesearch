package com.dm.platform.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dm.platform.util.EhCacheUtil;
import com.dm.platform.util.RandomValidateCode;



public class CommonListener extends ContextLoaderListener {
	private static Logger log = LogManager.getLogger(CommonListener.class);

	private static WebApplicationContext ct;
    
	public CommonListener() {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			System.out.println("----------------------授权通过---------------------");
			System.out.println("------------------------------------------------");
			System.out.println("----------------                ----------------");
			System.out.println("----------------  topieCms正在启动      ---------------");
			System.out.println("----------------                ----------------");
			System.out.println("------------------------------------------------");
			ct = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			initRandomVcode();
			EhCacheUtil.getInstance().init(ct);
			log.info("----------------topieCms启动成功！----------------");
		} catch (Exception ce) {
			ce.printStackTrace();
			log.error("初始化数据异常：", ce);
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	public void initRandomVcode(){
		RandomValidateCode.getInstance().getRandcode();
	}

}
