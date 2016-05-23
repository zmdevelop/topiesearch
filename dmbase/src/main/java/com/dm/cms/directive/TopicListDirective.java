package com.dm.cms.directive;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.dm.webservice.util.TopicServiceStub;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 *
 * Create by wjl 2016年4月21日 下午3:30:27
 * com.dm.cms.directive.TopicDirective.java
 * Project dmbase
 */
public class TopicListDirective implements TemplateDirectiveModel{

	private Logger log  = LoggerFactory.getLogger(TopicListDirective.class);
	
	@Value("${webservice.topic.url}")
	private String webServiceURL;
	

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer pageSize = params.get("pageSize") == null ? 8 : Integer.valueOf(params.get("pageSize")
				.toString());
		Integer pageNum = params.get("pageNum") == null ? 1 : Integer
				.valueOf(params.get("pageNum").toString());
		
		String tab = String.valueOf(params.get("tab") != null ? params.get("tab")
				.toString() : "all");
		List topics = new ArrayList();
		String page = "";
		try {
			TopicServiceStub topic =new  TopicServiceStub(this.webServiceURL+".topicServiceHttpSoap12Endpoint/");
			TopicServiceStub.GetTopicList gg = new TopicServiceStub.GetTopicList();
			gg.setPageNum(pageNum);
			gg.setPageSize(pageSize);
			gg.setTab(tab);
			page = topic.getTopicList(gg).get_return();
			/*RPCServiceClient serviceClient;
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			options.setProperty(
					org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT,
					new Integer(48000000));
			EndpointReference targetEPR = new EndpointReference(webServiceURL);
			options.setTo(targetEPR);
			QName opGetAllLegalInfor = new QName(qName, "getTopicList");
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("pageNum",pageNum);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("tab", tab);
			String jsonStr = jsonObject.toString();
			Object[] opGetAllLegalInforArgs = new Object[] {jsonStr};
			Class[] returnTypes = new Class[] { String.class };
			Object[] response = serviceClient.invokeBlocking(
					opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
			page = (String) response[0];*/
			parserJson(page,topics);
		} catch (RemoteException e) {
			log.error("远程调用bbs查询最新话题异常");
		}
		env.setVariable("topics",ObjectWrapper.DEFAULT_WRAPPER.wrap(topics));
		body.render(env.getOut()); 
		
	}

	private void parserJson(String page, List topics) {
		JSONObject pageObject = JSONObject.fromObject(page);
		JSONArray list = pageObject.getJSONArray("list");
		for(Object c:list){
			JSONObject json = JSONObject.fromObject(c);
			Map ic = new HashMap();
			ic.put("id",json.getString("id"));
			ic.put("title",json.getString("title"));
			ic.put("url",json.getString("url"));
			ic.put("nickName", json.getString("nickName"));
			ic.put("avatar", json.getString("avatar"));
			ic.put("inTime",json.getString("inTime"));
			topics.add(ic);
		}
		
	}

}

