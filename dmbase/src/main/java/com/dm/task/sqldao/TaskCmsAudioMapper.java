package com.dm.task.sqldao;

import com.dm.task.model.CmsAudio;

public interface TaskCmsAudioMapper extends BaseMapper<CmsAudio>{

    int updateByPrimaryKeyWithBLOBs(CmsAudio record);

}