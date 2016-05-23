package com.dm.task.sqldao;

import com.dm.task.model.CmsContent;

public interface TaskCmsContentMapper extends BaseMapper<CmsContent>{

    int updateByPrimaryKeyWithBLOBs(CmsContent record);

}