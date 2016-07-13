package com.dm.search.model;

public class SearchEntity {
	private Integer id;

	private String entityName;

	private String datasource;

	private String tableName;

	private String idFiled;

	private String titleFiled;

	private String urlFiled;

	private String authorFiled;

	private String contentFiled;

	private String channelFiled;

	private String publishtimeFiled;

	private String selectFileds;

	private String leftJoinTables;

	private String onFileds;

	private String whereFiled;

	private Boolean transformerHtml;

	private String deltaQuery;

	private String query;

	private Integer pid = 0;

	private String deltaImportQuery;
	
	private String status ;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeltaImportQuery() {
		String where = " " + idFiled + "='${dataimporter.delta.id}'";
		if (getQuery() == null) {
			return "";
		}
		if (getQuery().toUpperCase().indexOf(" WHERE ") == -1) {
			where = " where" + where;
		} else {
			where = " and" + where;
		}
		return getQuery() + where;
		// return deltaImportQuery;
	}

	public void setDeltaImportQuery(String deltaImportQuery) {
		this.deltaImportQuery = deltaImportQuery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName == null ? null : entityName.trim();
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource == null ? null : datasource.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getIdFiled() {
		return idFiled;
	}

	public void setIdFiled(String idFiled) {
		this.idFiled = idFiled == null ? null : idFiled.trim();
	}

	public String getTitleFiled() {
		return titleFiled;
	}

	public void setTitleFiled(String titleFiled) {
		this.titleFiled = titleFiled == null ? null : titleFiled.trim();
	}

	public String getUrlFiled() {
		return urlFiled;
	}

	public void setUrlFiled(String urlFiled) {
		this.urlFiled = urlFiled == null ? null : urlFiled.trim();
	}

	public String getAuthorFiled() {
		return authorFiled;
	}

	public void setAuthorFiled(String authorFiled) {
		this.authorFiled = authorFiled == null ? null : authorFiled.trim();
	}

	public String getContentFiled() {
		return contentFiled;
	}

	public void setContentFiled(String contentFiled) {
		this.contentFiled = contentFiled == null ? null : contentFiled.trim();
	}

	public String getChannelFiled() {
		return channelFiled;
	}

	public void setChannelFiled(String channelFiled) {
		this.channelFiled = channelFiled == null ? null : channelFiled.trim();
	}

	public String getPublishtimeFiled() {
		return publishtimeFiled;
	}

	public void setPublishtimeFiled(String publishtimeFiled) {
		this.publishtimeFiled = publishtimeFiled == null ? null
				: publishtimeFiled.trim();
	}

	public String getSelectFileds() {
		return selectFileds;
	}

	public void setSelectFileds(String selectFileds) {
		this.selectFileds = selectFileds == null ? null : selectFileds.trim();
	}

	public String getLeftJoinTables() {
		return leftJoinTables;
	}

	public void setLeftJoinTables(String leftJoinTables) {
		this.leftJoinTables = leftJoinTables == null ? null : leftJoinTables
				.trim();
	}

	public String getOnFileds() {
		return onFileds;
	}

	public void setOnFileds(String onFileds) {
		this.onFileds = onFileds == null ? null : onFileds.trim();
	}

	public String getWhereFiled() {
		return whereFiled;
	}

	public void setWhereFiled(String whereFiled) {
		this.whereFiled = whereFiled == null ? null : whereFiled.trim();
	}

	public Boolean getTransformerHtml() {
		return transformerHtml;
	}

	public void setTransformerHtml(Boolean transformerHtml) {
		this.transformerHtml = transformerHtml;
	}

	public String getDeltaQuery() {
		if (pid != 0) {
			return "";
		}
		StringBuffer string = new StringBuffer("");
		String[] a = tableName.split(",");
		String mainTable = "";
		for (String str : a) {
			if (idFiled.contains(str)) {
				mainTable = str;
				break;
			}
		}
		string.append("SELECT ").append(idFiled).append(" FROM ")
				.append(mainTable).append(" WHERE ").append(publishtimeFiled)
				.append(" >'${dataimporter.last_index_time}'");
		if (whereFiled != null && !"".equals(whereFiled)) {
			string.append(" AND ").append(whereFiled);
		}
		return string.toString();
	}

	public void setDeltaQuery(String deltaQuery) {
		this.deltaQuery = deltaQuery == null ? null : deltaQuery.trim();
	}

	public String getQuery() {
		if (pid != 0) {
			return "";
		}
		StringBuffer bufferString = new StringBuffer(" SELECT ");
		bufferString.append("concat(").append(idFiled).append(",'_")
				.append(entityName).append("') as id,").append(titleFiled)
				.append(" as title,").append(urlFiled)
				.append(" as url ");
		if (contentFiled != null && !contentFiled.equals("")) {
			bufferString.append(",").append(contentFiled)
					.append(" as content ");
		}
		if (publishtimeFiled != null && !publishtimeFiled.equals("")) {
			bufferString.append(",").append("STR_TO_DATE(")
					.append(publishtimeFiled)
					.append(",'%Y-%m-%d %H:%i:%s') as publishDate ");
		}
		if (authorFiled != null && !"".equals(authorFiled)) {
			bufferString.append(",").append(authorFiled).append(" as origin ");
		}
		if (channelFiled != null && !"".equals(channelFiled)) {
			bufferString.append(",").append(channelFiled)
					.append(" as displayName ");
		}
		String[] fromtables = tableName.split(",");
		/*
		 * String mainTable = ""; String table = null; for (String str : a) { if
		 * (idFiled.contains(str)) { mainTable = str; } else { table = str; } }
		 */
		bufferString.append(" FROM ").append(fromtables[0]);
		String[] ons = {};
		if (onFileds != null && !onFileds.equals("")) {
			ons = onFileds.split(",");
		}
		if (ons.length == 2 * (fromtables.length - 1)) {
			for (int i = 1; i < fromtables.length; i++) {
				String table = fromtables[i];
				bufferString.append(" LEFT JOIN ").append(table);
				bufferString.append(" ON ").append(ons[2 * (i - 1)])
						.append("=").append(ons[2 * (i - 1) + 1]);
			}
		}
		if (whereFiled != null && !"".equals(whereFiled)) {
			bufferString.append(" WHERE ").append(whereFiled);
		}
		return bufferString.toString();
	}

	public void setQuery(String query) {
		this.query = query == null ? null : query.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}