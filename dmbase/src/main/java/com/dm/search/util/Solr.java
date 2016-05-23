package com.dm.search.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class Solr {
 private static SolrClient solr = null;
	
	public static SolrClient getHostSolr() {
		try {
			solr = new HttpSolrClient("http://localhost:8983/solr");

		} catch (Exception e) {

			System.out.println("请检查tomcat服务器或端口是否开启!");

			e.printStackTrace();
			return null;

		}
		return solr;
	}
	
}
