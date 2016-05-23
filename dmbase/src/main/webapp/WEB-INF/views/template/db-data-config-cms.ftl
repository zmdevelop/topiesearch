<dataConfig>
	<#list dataSources as dataSource>
		<dataSource name="${dataSource.name}" driver="${dataSource.driver}" type="JdbcDataSource"
		url="${dataSource.url}"
		user="${dataSource.user}" password="${dataSource.password}" />
	</#list>
	<dataSource name="bin_url_ds" type="BinURLDataSource"  connectionTimeout="5000" readTimeout="10000" />
	<!--<dataSource name="url_ds" type="URLDataSource"  connectionTimeout="5000" readTimeout="10000" />-->
	<!--<dataSource name="ds2" type="BinFileDataSource" /> -->
	<document>
	<#list entitys as entity>
		<entity dataSource="${entity.datasource}" name="${entity.entityName}" onError = "Skip"
			query="${entity.query}"
			deltaQuery="${entity.deltaQuery}" deltaImportQuery="${entity.query}" >
			<field column="${entity.idFiled}" name="id" />
			<field column="${entity.titleFiled}" name="title" />
			<field column="${entity.publishtimeFiled}" name="publishDate" />
			<field column="${entity.authorFiled}" name="origin" />
			<field column="${entity.channelFiled}" name="displayName" />
			<field column="${entity.contentFiled}" name="content" 
			<#if entity.transformerHtml==true >
				stripHTML="true" regex="[/ \t\n\r]" replaceWith="" 
			</#if>
			/>
			<field column="${entity.urlFiled}" name="url" />
		</entity>
	</#list>
		<entity dataSource="ds1" name="cmsContent" onError = "Skip"
			query="SELECT concat(c.id,'_content') as id,c.title,c.content_text,c.publish_date,c.origin,ch.display_name,c.url FROM cms_content c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1" transformer="HTMLStripTransformer,RegexTransformer"
			deltaQuery="select id from cms_content where is_html = 1 and publish_date >'${dataimporter.last_index_time}'" deltaImportQuery="SELECT concat(c.id,'_content') as id,c.title,c.content_text,c.publish_date,c.origin,ch.display_name,c.url FROM cms_content c LEFT JOIN cms_channel 
			ch ON c.channel_id=ch.id where c.is_html = 1 and c.id='${dataimporter.delta.id}'" >
			<field column="id" name="id" />
			<field column="title" name="title" />
			<field column="publish_date" name="publishDate" />
			<field column="origin" name="origin" />
			<field column="display_name" name="displayName" />
			<field column="content_text" name="content" stripHTML="true" regex="[/ \t\n\r]"
replaceWith=""/>
			<field column="url" name="url" />
		</entity>
		<entity dataSource="ds1" name="atta" onError = "Skip"
			query="SELECT  concat(c.id,'_',ca.attachment_id,'_content') as id,c.publish_date,a.attachment_name,c.origin,c.url,c.title,a.attachment_url,a.id as aid
				FROM cms_content_attachment ca
				LEFT JOIN cms_content c ON c.id = ca.content_id
				LEFT JOIN cms_attachment a ON a.id = ca.attachment_id
				WHERE c.is_html = 1"
			deltaQuery="select id from cms_content where is_html = 1 and publish_date >'${dataimporter.last_index_time}'" 
			deltaImportQuery = "SELECT  concat(c.id,'_',ca.attachment_id,'_content') as id,c.publish_date,a.attachment_name,c.origin,c.url,c.title,a.attachment_url,a.id as aid
				FROM cms_content_attachment ca
				LEFT JOIN cms_content c ON c.id = ca.content_id
				LEFT JOIN cms_attachment a ON a.id = ca.attachment_id
				WHERE c.is_html = 1 and c.id='${dataimporter.delta.id}'"
				>
			<field column="id" name="id" />
			<field column="title" name="title" />
			<field column="url" name="url" />
			<field column="origin" name="origin" />
			<!--<field column="attachment_name" name="title" />
			<field column="attachment_url" name="url" />-->
			<field column="publish_date" name="publishDate" />
			<entity name="atta_content" dataSource="bin_url_ds" processor="TikaEntityProcessor" onError = "Skip" url="${atta.attachment_url}" format="text" >
				<field column="text" name="content"  meta="true" />
				<!--<field column="Author" name="origin" meta="true" />-->
			</entity>
		</entity>
		<entity dataSource="ds1" name="cmsAudio" onError = "Skip"
			query="SELECT concat(c.id,'_audio') as id,c.image_url,c.singer,c.publish_date,c.`name` ,c.url,c.introduce ,ch.display_name FROM cms_audio c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1" 
			deltaQuery="select id from cms_audio where is_html = 1 and publish_date >'${dataimporter.last_index_time}'" 
			deltaImportQuery="SELECT  concat(c.id,'_audio') as id,c.image_url,c.publish_date,c.`name`,c.url,c.introduce ,ch.display_name FROM cms_audio c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1 and c.id='${dataimporter.delta.id}'" >
			<field column="id" name="id" />
			<field column="name" name="name" />
			<field column="publish_date" name="publishDate" />
			<field column="singer" name="singer" />
			<field column="display_name" name="displayName" />
			<field column="introduce" name="introduce" />
			<field column="url" name="url" />
		</entity>
		<entity dataSource="ds1" name="cmsNovel" onError = "Skip"
			query="SELECT c.name, concat(c.id,'_novel') as id,c.introduce,c.publish_date,c.image_url,c.url,ch.display_name FROM cms_novel c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1" 
			deltaQuery="select id from cms_audio where is_html = 1 and publish_date >'${dataimporter.last_index_time}'" 
			deltaImportQuery="SELECT c.name , concat(c.id,'_novel') as id,c.introduce,c.publish_date,c.image_url,c.url,ch.display_name FROM cms_novel c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1 and c.id='${dataimporter.delta.id}'" >
			<field column="id" name="id" />
			<field column="name" name="name" />
			<field column="publish_date" name="publishDate" />
			<field column="author" name="author" />
			<field column="display_name" name="displayName" />
			<field column="introduce" name="introduce" />
			<field column="url" name="url" />
		</entity>
		<entity dataSource="ds1" name="cmsVideo" onError = "Skip"
			query="SELECT  concat(c.id,'_video') as id,c.name,c.director,c.image_url,c.introduce,c.image_url,c.url,ch.display_name FROM cms_video c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1" 
			deltaQuery="select id from cms_audio where is_html = 1 and publish_date >'${dataimporter.last_index_time}'" 
			deltaImportQuery="SELECT  concat(c.id,'_video') as id,c.director,c.image_url,c.introduce,c.image_url,c.url,ch.display_name FROM cms_video c LEFT JOIN cms_channel ch ON c.channel_id=ch.id where c.is_html = 1 and c.id='${dataimporter.delta.id}'" >
			<field column="id" name="id" />
			<field column="name" name="name" />
			<field column="image_url" name="image_url" />
			<field column="publish_date" name="publishDate" />
			<field column="director" name="director" />
			<field column="display_name" name="displayName" />
			<field column="introduce" name="introduce" />
			<field column="url" name="url" />
		</entity>
		
		<!--<entity  dataSource="bin_url_ds" processor="TikaEntityProcessor" url="http://127.0.0.1:80/html/resource/1450859014134.docx" format="text" name="docx">
			<field column="text" name="content"  meta="true" />
			<field column="Author" name="origin" meta="true" />
			<field column="Author" name="url" meta="true" />
			
			<field column="title" name ="id"/>
		</entity>
		<entity dataSource="ds1" name="urldata"
			query="SELECT t.attachment_name,t.attachment_url,t.id FROM cms_attachment t">
			<field column="id" name="id" />
			<field column="attachment_name" name="title" />
			<field column="attachment_url" name="url" />
			<entity name="f1_documents" dataSource="url_ds"
				processor="PlainTextEntityProcessor" url="${urldata.attachment_url}" format="text">
				<field column="plainText" name="content" />
			</entity>
		</entity>-->
		<!--<entity name="files" dataSource="null" rootEntity="false" processor="FileListEntityProcessor" 
			baseDir="D:\Tool\apache-tomcat-8.0.22\webapps\html\resource\" fileName=".*\.(doc)|(pdf)|(docx)|(txt)" 
			recursive="true"> 
			<field column="fileAbsolutePath" name="id" /> 
			<field column="fileSize" name="size" /> 
			<field column="fileLastModified" name="url" /> 
			<entity processor="TikaEntityProcessor" name="txtfile" url="${files.fileAbsolutePath}" dataSource="ds2"> 
				<field column="text" name="content"/> 
			</entity>
		</entity> --> 
	</document>
</dataConfig>

