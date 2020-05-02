package com.example.jizm.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="封装一张表中需同步的增、删、改记录")
public class SyncRecords<T> {
    @ApiModelProperty(value="数据表名")
    String tableName;

    @ApiModelProperty(value="该表是否需要同步")
    boolean needSync;

    @ApiModelProperty(value = "需要在服务器插入的记录列表")
    List<T> recordList;


    public boolean isNeedSync() {
        return needSync;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

}
