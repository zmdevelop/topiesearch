package com.dm.webservice.impl;


import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.aspectj.weaver.ast.Call;

import com.dm.platform.dao.CommonDAO;
import com.dm.platform.model.UserAccount;
import com.dm.webservice.SynVisitService;

public class SynVisitServiceImpl implements SynVisitService {
	
	@Resource
	private CommonDAO commonDAO;
	
	@Value("${webservice.url}")
	private String webServiceURL;
	
	@Value("${qname.url}")
	private String qName;
	//private String webServiceURL;
	//private String qName;
	@Async  
	@Override
	public String putInfo(String infoCode,String opType) {
		String notReadNum;
		try {
			RPCServiceClient serviceClient;
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			options.setProperty(
					org.apache.axis2.transport.http.HTTPConstants.CONNECTION_TIMEOUT,
					new Integer(48000000));
			EndpointReference targetEPR = new EndpointReference(webServiceURL);
			options.setTo(targetEPR);
			QName opGetAllLegalInfor = new QName(qName, "SynchronizedInfo");
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("opType", opType);
			jsonObject.put("infoCode", infoCode);
			String jsonStr = jsonObject.toString();
			Object[] opGetAllLegalInforArgs = new Object[] {jsonStr};
			Class[] returnTypes = new Class[] { String.class };
			Object[] response = serviceClient.invokeBlocking(
					opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
			notReadNum = (String) response[0];
		} catch (RemoteException e) {
			e.printStackTrace();
			notReadNum = "远程服务调用异常";
		}
		return notReadNum;
	}

	

}
