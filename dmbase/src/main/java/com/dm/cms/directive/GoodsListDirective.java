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

import com.dm.webservice.util.GoodsServiceStub;
import com.dm.webservice.util.GoodsServiceStub.EGoods;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class GoodsListDirective  implements TemplateDirectiveModel{

	private Logger log  = LoggerFactory.getLogger(GoodsListDirective.class);
	
	@Value("${webservice.goods.url}")
	private String webServiceURL;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer pageSize = params.get("pageSize") == null ? 8 : Integer.valueOf(params.get("pageSize")
				.toString());
		Integer pageNum = params.get("pageNum") == null ? 1 : Integer
				.valueOf(params.get("pageNum").toString());
		
		String type = String.valueOf(params.get("type") != null ? params.get("type")
				.toString() : "");
		String sort = String.valueOf(params.get("sort") != null ? params.get("sort")
				.toString() : "1");
		EGoods[] page =new GoodsServiceStub.EGoods[]{};
		try {
			GoodsServiceStub goods =new  GoodsServiceStub(this.webServiceURL+".goodsServiceHttpSoap12Endpoint/");
			GoodsServiceStub.GetGoodsList gg = new GoodsServiceStub.GetGoodsList();
			gg.setPageNum(pageNum);
			gg.setPageSize(pageSize);
			gg.setType(type);
			gg.setSort(sort);
			page = goods.getGoodsList(gg).get_return();
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
			//parserJson(page,topics);
		} catch (RemoteException e) {
			log.error("远程调用bbs查询最新话题异常");
		}
		env.setVariable("goodss",ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
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
