import java.io.IOException;
import java.util.Date;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;


public class T {  
  
    public static final String SOLR_URL = "http://localhost:8080/solr/cms_core";  
      
    public static void main(String[] args) {  
  
        commit();  
    }  
      
    public static void commit(){  
        HttpSolrServer solr=null;  
          
        try{  
            solr = new HttpSolrServer(SOLR_URL);  
        }catch(Exception e){  
              
        }  
        solr.setSoTimeout(1000);  
          
        for(int i=0;i<100;i++){  
            SolrInputDocument sid = new SolrInputDocument();  
            sid.addField("id", i);  
            sid.addField("name", "struts+hibernate+spring 开发大全" + i);  
            sid.addField("title", "三种框架的综合应用" + i);  
            sid.addField("author", "xxxx"+i);  
            //sid.addField("date", new Date());  
            sid.addField("content", "高级应用类书籍" + i);  
            sid.addField("publishDate",new Date());  
              
            try {  
                solr.add(sid);  
            } catch (SolrServerException | IOException e) {  
                e.printStackTrace();  
            }  
        }  
          
        try {  
            //优化索引  
            solr.optimize();  
            solr.commit();  
        } catch (SolrServerException | IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println("--------提交完成-------");  
    }  
  
}  
