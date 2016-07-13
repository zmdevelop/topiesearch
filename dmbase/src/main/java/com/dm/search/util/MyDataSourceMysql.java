package com.dm.search.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyDataSourceMysql extends MyDataSource{
	public static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String MYSQL_TABLE_QUERY_SQL = "select TABLE_NAME from information_schema.columns where table_schema = ? GROUP BY TABLE_NAME;";
	public static final String MYSQL_COLUMN_QUERY_SQL = "select column_name , data_type from information_schema.columns where table_schema = ? and TABLE_NAME = ?";
	public static final String QUERY_PRIMARY_KEY = "select Column_Name from information_schema.columns where table_schema = ? and TABLE_NAME = ? and column_key='pri'";
	
	
	@Override
	public List showDataBases() {
		List<Map> orglist=new ArrayList<Map>();
		Statement stat=null;
		try {
			stat = this.conection.createStatement();
			ResultSet rs = stat.executeQuery(MYSQL_COLUMN_QUERY_SQL);
			while (rs.next()) {
				Map org =new HashMap();
				org.put("schemaName", rs.getString(1));
				orglist.add(org);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orglist;
	}

	@Override
	List showTables(String database) {
		List<Map> orglist=new ArrayList<Map>();
		PreparedStatement stat =null;
		try {
			stat = this.conection.prepareStatement(MYSQL_TABLE_QUERY_SQL);
			stat.setString(1, database);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Map org =new HashMap();
				org.put("tableName", rs.getString(1));
				orglist.add(org);
			}
		} catch (Exception e) {
			try {
				stat.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return orglist;
	}

	@Override
	List showColumns(String database, String table) {
		List<Map> orglist=new ArrayList<Map>();
		PreparedStatement stat =null;
		try {
			stat = this.conection.prepareStatement(MYSQL_COLUMN_QUERY_SQL);
			stat.setString(1, database);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Map org =new HashMap();
				org.put("columnName", rs.getString(1));
				org.put("columnType", rs.getString(2));
				orglist.add(org);
			}
		} catch (Exception e) {
			try {
				stat.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return orglist;
	}

}
