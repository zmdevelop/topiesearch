package com.dm.dynamicbean.dao.Imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.dm.dynamicbean.dao.IDataBaseDao;
import com.dm.dynamicbean.model.Column;
import com.dm.dynamicbean.model.DataBase;
import com.dm.dynamicbean.model.Table;
import com.dm.dynamicbean.util.JdbcTemplateManager;
import com.dm.search.model.SearchDataSource;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 *
 * Create by wjl 2016年7月6日 下午1:08:21
 * com.dm.dynamicbean.dao.Imp.DataBaseDaoImpl.java
 * Project dmbase
 */
@Service
public class DataBaseDaoImpl implements IDataBaseDao {

	@SuppressWarnings("serial")
	private static final Set<String> INTERNAL_DB_NAME_SET = Collections
			.unmodifiableSet(new HashSet<String>() {
				{
					add("mysql");
					add("information_schema");
					add("performance_schema");
				}
			});

	@Override
	public List<DataBase> getDataBaseListByAddress(String address) {
		if (StringUtils.isEmpty(address)) {
			return Collections.emptyList();
		}
		JdbcTemplate template = JdbcTemplateManager
				.getJdbcTemplateByAddress(address);
		if (template == null) {
			return Collections.emptyList();
		}
		StringBuilder sqlBuff = new StringBuilder(
				"select * from information_schema.schemata where 1 = 1 ");
		for (String internalDbName : INTERNAL_DB_NAME_SET) {
			sqlBuff.append(" and schema_name != '").append(internalDbName)
					.append("' ");
		}
		sqlBuff.append(" order by schema_name ");
		return template.query(sqlBuff.toString(),
				new DataBase.DataBaseRowMapper());
	}

	@Override
	public DataBase getDataBaseByAddressAndDbName(String address, String dbName) {
		if (StringUtils.isEmpty(address) || StringUtils.isEmpty(dbName)
				|| INTERNAL_DB_NAME_SET.contains(dbName)) {
			return null;
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(address);
		if (template == null) {
			return null;
		}
		String sql = "select * from information_schema.schemata where schema_name = :database";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", dbName);
		return template.queryForObject(sql, params,
				new DataBase.DataBaseRowMapper());
	}

	@Override
	public List<Table> getTableListByAddressAndDatabase(String address,
			String database) {
		if (StringUtils.isEmpty(address) || StringUtils.isEmpty(database)) {
			return Collections.emptyList();
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(address);
		if (template == null) {
			return Collections.emptyList();
		}
		String sql = "select table_schema, table_name, engine, table_collation, table_comment from information_schema.tables where table_schema = :database order by table_name ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", database);
		return template.query(sql, params, new Table.TableRowMapper());
	}

	@Override
	public List<Column> getColumnListByAddressAndDatabase(String address,
			String database) {
		if (StringUtils.isEmpty(address) || StringUtils.isEmpty(database)) {
			return Collections.emptyList();
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(address);
		if (template == null) {
			return Collections.emptyList();
		}
		String sql = "select * from information_schema.columns where table_schema = :database order by table_name, ordinal_position";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", database);
		return template.query(sql, params, new Column.ColumnRowMapper());
	}

	@Override
	public List<DataBase> getDataBaseListByAddress(SearchDataSource dataSource) {
		if (StringUtils.isEmpty(dataSource.getAddress())) {
			return Collections.emptyList();
		}
		JdbcTemplate template = JdbcTemplateManager
				.getJdbcTemplateByAddress(dataSource);
		if (template == null) {
			return Collections.emptyList();
		}
		StringBuilder sqlBuff = new StringBuilder(
				"select * from information_schema.schemata where 1 = 1 ");
		for (String internalDbName : INTERNAL_DB_NAME_SET) {
			sqlBuff.append(" and schema_name != '").append(internalDbName)
					.append("' ");
		}
		sqlBuff.append(" order by schema_name ");
		return template.query(sqlBuff.toString(),
				new DataBase.DataBaseRowMapper());
	}

	@Override
	public List<Table> getTableListByAddressAndDatabase(
			SearchDataSource dataSource, String database) {
		if(database==null|| database.equals("")){
			database = dataSource.getDatabase();
		}
		if (StringUtils.isEmpty(dataSource.getAddress())
				|| StringUtils.isEmpty(database)) {
			return Collections.emptyList();
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(dataSource);
		if (template == null) {
			return Collections.emptyList();
		}
		String sql = "select table_schema, table_name, engine, table_collation, table_comment from information_schema.tables where table_schema = :database order by table_name ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", database);
		return template.query(sql, params, new Table.TableRowMapper());
	}

	@Override
	public List<Column> getColumnListByAddressAndDatabase(
			SearchDataSource dataSource,String SchemaName, String tableName) {
		if (StringUtils.isEmpty(dataSource.getAddress())
				|| StringUtils.isEmpty(tableName)) {
			return Collections.emptyList();
		}
		if(SchemaName==null || SchemaName.equals("")){
			SchemaName = dataSource.getDatabase();
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(dataSource);
		if (template == null) {
			return Collections.emptyList();
		}
		String sql = "select * from information_schema.columns where table_schema = :database and table_name in (:tableName) order by table_name, ordinal_position";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", SchemaName);
		List list = new ArrayList();
		list = Arrays.asList(tableName.split(","));
		params.put("tableName", list);
		return template.query(sql, params, new Column.ColumnRowMapper());
	}

	@Override
	public DataBase getDataBaseByAddressAndDbName(SearchDataSource dataSource,
			String dbName) {
		if (StringUtils.isEmpty(dataSource.getAddress()) || StringUtils.isEmpty(dbName)
				|| INTERNAL_DB_NAME_SET.contains(dbName)) {
			return null;
		}
		NamedParameterJdbcTemplate template = JdbcTemplateManager
				.getNamedParameterJdbcTemplateByAddress(dataSource);
		if (template == null) {
			return null;
		}
		String sql = "select * from information_schema.schemata where schema_name = :database";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("database", dbName);
		return template.queryForObject(sql, params,
				new DataBase.DataBaseRowMapper());
	}

}
