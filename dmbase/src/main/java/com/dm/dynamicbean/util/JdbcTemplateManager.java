package com.dm.dynamicbean.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dm.search.model.SearchDataSource;
import com.dm.search.sqldao.SearchDataSourceMapper;

public class JdbcTemplateManager {
	public static final String DEFAULT_DATA_SOURCE_BEAN_NAME_PREFIX = "DB_";
	private static Map<String, NamedParameterJdbcTemplate> jdbcTemplatePool = new HashMap<String, NamedParameterJdbcTemplate>();
	
	public static JdbcTemplate getJdbcTemplateByAddress(String address) {
		NamedParameterJdbcTemplate npjt = getNamedParameterJdbcTemplateByAddress(address);
		return npjt == null? null: (JdbcTemplate) npjt.getJdbcOperations();
	}
	
	public static JdbcTemplate getJdbcTemplateByAddress(String address, String username, String password) {
		NamedParameterJdbcTemplate npjt = getNamedParameterJdbcTemplateByAddress(address, username, password);
		return npjt == null? null: (JdbcTemplate) npjt.getJdbcOperations();
	}
	
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplateByAddress(String address) {
		SearchDataSourceMapper searchDataSourceMapper = SpringUtil.getBean(SearchDataSourceMapper.class);
		SearchDataSource d = searchDataSourceMapper.selectByAddress(address);
		return getNamedParameterJdbcTemplateByAddress(address, d.getUser(), d.getPassword());
	}
	public static JdbcTemplate getJdbcTemplateByAddress(SearchDataSource dataSource) {
		NamedParameterJdbcTemplate npjt = getNamedParameterJdbcTemplateByAddress(dataSource);
		return npjt == null? null: (JdbcTemplate) npjt.getJdbcOperations();
	}
	
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplateByAddress(String address, String username, String password) {
		if(StringUtils.isEmpty(address)) {
			return null;
		}
		NamedParameterJdbcTemplate template = jdbcTemplatePool.get(address);
		if(template != null) {
			return template;
		}
		DataSource dataSource = SpringUtil.addDataSourceBean(address, username, password);
		if(dataSource == null) {
			return null;
		}
		template = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplatePool.put(address, template);
		return template;
	}
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplateByAddress(SearchDataSource searchDataSource) {
		if(StringUtils.isEmpty(searchDataSource.getAddress())) {
			return null;
		}
		NamedParameterJdbcTemplate template = jdbcTemplatePool.get(DEFAULT_DATA_SOURCE_BEAN_NAME_PREFIX+searchDataSource.getName());
		if(template != null) {
			return template;
		}
		DataSource dataSource = SpringUtil.addDataSourceBean(searchDataSource);
		if(dataSource == null) {
			return null;
		}
		template = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplatePool.put(DEFAULT_DATA_SOURCE_BEAN_NAME_PREFIX+searchDataSource.getName(), template);
		return template;
	}
	
}
