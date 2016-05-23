
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dm.platform.model.UserAccount;
import com.dm.task.service.ObjectSerilaze;

import redis.clients.jedis.Jedis;

public class RedisTest {
    private Jedis jedis; 
    
    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("192.168.3.126", 6379);
        //权限认证
        //jedis.auth("admin");  
    }
    
    /**
     * redis存储字符串
     */
    
    @Test
    public void testString() {
        //-----添加数据----------
    	ObjectSerilaze ob = new ObjectSerilaze();
      try {
		UserAccount user =  (UserAccount) ob.deSeialize(jedis.get("1".getBytes()));
		System.out.println(user.getLoginname()+"--"+user.getPassword());
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//向key-->name中放入了value-->xinxin  
        
        
    }
    
    @Test
    public void testGetString() {
        //-----添加数据----------
    	ObjectSerilaze ob = new ObjectSerilaze();
      try {
    	  UserAccount user = new UserAccount();
    	  user.setCode("1");
    	  user.setLoginname("admin1");
    	  jedis.set("1".getBytes(), ob.serialize(user));
		//UserAccount user =  (UserAccount) ob.deSeialize(jedis.get("1".getBytes()));
		//System.out.println(user.getLoginname()+"--"+user.getPassword());
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//向key-->name中放入了value-->xinxin  
    }
    
   
}