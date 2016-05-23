import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.dm.search.sqldao.DatabaseMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/applicationContext.xml"})
public class DataTables {
	
	@Autowired DatabaseMapper cmsSiteService;
	@Ignore
    @Test public void list(){
		System.out.println("==================================");
        List<Map> page = cmsSiteService.selectTables(null);
        for(Map m:page){
//        	System.out.println(m.get("tableName")+"==================================");
        	
        }
    }
	
	
}
