<dataConfig>
	<#list dataSources as dataSource>
		<#if dataSource.style == '1'>
		<dataSource name="${dataSource.name}" driver="${dataSource.driver}" type="${dataSource.dataSourceType}"
		url="${dataSource.url}"
		user="${dataSource.user}" password="${dataSource.password}" />
		<#elseif dataSource.style == '2'>
		<dataSource name="${dataSource.name}" type="${dataSource.dataSourceType}"  connectionTimeout="5000" readTimeout="10000" />
		<#elseif dataSource.style == '3'>
		<dataSource name="${dataSource.name}" type="${dataSource.dataSourceType}"  />
		<#else>
		<!--有误的数据-->
		<dataSource name="${dataSource.name}" type="${dataSource.dataSourceType}"  />
		</#if>
	</#list>
	
	<!--<dataSource name="url_ds" type="URLDataSource"  connectionTimeout="5000" readTimeout="10000" />-->
	<!--<dataSource name="ds2" type="BinFileDataSource" /> -->
	<document>
	<#list entitys as entity>
		<#if entity.pid ==0>
		
		<entity name="${entity.entityName}" dataSource="${entity.datasource}" onError = "Skip"
			query="${entity.query}"
			deltaQuery="${entity.deltaQuery}"
			deltaImportQuery="${entity.deltaImportQuery}" >
			<field column="${entity.idFiled}" name="id" />
			<field column="${entity.titleFiled}" name="title" />
			<#if entity.publishtimeFiled != null>
			<field column="${entity.publishtimeFiled}" name="publishDate" />
			</#if>
			<#if entity.authorFiled != null>
			 <field column="${entity.authorFiled}" name="origin" />
			</#if>
			<#if entity.channelFiled != null>
			<field column="${entity.channelFiled}" name="displayName" />
			</#if>
			<#if entity.contentFiled!=null>
			<field column="${entity.contentFiled}" name="content" <#if entity.transformerHtml==true > stripHTML="true" regex="[/ \t\n\r]" replaceWith="" </#if>/>
			</#if>
			<#list entitys as entity2>
				<#if entity.id==entity2.pid>
				<entity name="${entity2.entityName}" dataSource="${entity2.datasource}" 
					processor="${entity2.processor!'TikaEntityProcessor'}" onError = "Skip" url="${r"${"}${entity.entityName+'.'+entity2.urlFiled}}" format="text" >
					<field column="text" name="content"  meta="true" />
					<!--<field column="Author" name="origin" meta="true" />-->
				</entity>
				</#if>
			</#list>
			<field column="${entity.urlFiled}" name="url" />
			
		</entity>
		</#if>
	</#list>
		
	</document>
</dataConfig>

