package com.dm.search.model;

import java.util.ArrayList;
import java.util.List;

public class SearchDataSource {
	// {typeflag , type , driver , datasourcestyleflag , processor} 
	public static final String[] t_mysql = { "MYSQL", "JdbcDataSource","com.mysql.jdbc.Driver","1","" ,"jdbc:mysql://${address}:3306/${database}"};
	public static final String[] t_oracle = { "ORACLE", "JdbcDataSource","oracle.jdbc.driver.OracleDriver","1","" ,"jdbc:oracle:thin:@${address}:1521:${database}"};
	public static final String[] t_sqlServer ={"SQLSERVER", "JdbcDataSource","com.microsoft.sqlserver.jdbc.SQLServerDriver","1","","jdbc:sqlserver://${address}:1433;DatabaseName=${database}"};
	public static final String[] t_db2 = { "DB2", "JdbcDataSource","com.ibm.db2.jcc.DB2Driver","1" ,"",""};
	public static final String[] t_url_word = { "URLWDOC", "URLDataSource","","2","TikaEntityProcessor","" };
	public static final String[] t_word = { "DOC", "BinFileDataSource","" ,"3","TikaEntityProcessor",""};
	public static final String[] t_pdf = { "PDF", "BinFileDataSource","","3" ,"TikaEntityProcessor",""};
	public static List<String[]> typeList=new ArrayList<String[]>();
	{
		typeList.add(t_oracle);
		typeList.add(t_db2);
		typeList.add(t_mysql);
		typeList.add(t_pdf);
		typeList.add(t_pdf);
		typeList.add(t_word);
		typeList.add(t_url_word);
		
	}

	
    private Integer id;

    private String name;

    private String type;
    
    private String dataSourceType;

    private String user;

    private String password;

    private String url;

    private String driver;
    
    private String style;
    
    private String processor;
    
    private String address;
    
    private String database;
    
    public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProcessor() {
    	for(String[] arr:typeList){
    		if(arr[0].equals(type)){
    			processor = arr[4];
    			break;
    		}
    	}
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getDataSourceType() {
		for(String[] arr:typeList){
			if(arr[0].equals(type)){
				dataSourceType = arr[1];
				break;
			}
		}
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getStyle() {
		for(String[] arr:typeList){
			if(arr[0].equals(type)){
				style = arr[3];
				break;
			}
		}
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUrl() {
    	for(String[] arr:typeList){
    		if(arr[0].equals(type)){
    			url = arr[5];
    			if(url.equals(""))
    				return "";
    			break;
    		}
    	}
    	url =url.replace("${address}", address==null?"":address);
    	url =url.replace("${database}", database==null?"":database);
    	return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDriver() {
    	if(driver!=null) {return driver;}
    	for(String[] arr:typeList){
    		if(arr[0].equals(type)){
    			driver = arr[2];
    			break;
    		}
    	}
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }
}