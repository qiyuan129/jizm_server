package com.example.jizm.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="封装一张表中需同步的增、删、改记录")
public class SyncRecords<T> {
    @ApiModelProperty(value="该表是否需要同步")
    boolean needSync;
    @ApiModelProperty(value = "需要在服务器插入的记录列表")
    List<T> insertList;
    @ApiModelProperty(value = "需要在服务器更新的记录列表")
    List<T> updateList;
    @ApiModelProperty(value = "需要在服务器删除的记录列表")
    List<T> deleteList;

    public boolean isNeedSync() {
        return needSync;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    public List<T> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<T> insertList) {
        this.insertList = insertList;
    }

    public List<T> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<T> updateList) {
        this.updateList = updateList;
    }

    public List<T> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<T> deleteList) {
        this.deleteList = deleteList;
    }
}
