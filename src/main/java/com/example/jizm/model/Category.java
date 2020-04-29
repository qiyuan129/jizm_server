package com.example.jizm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="账单类别类")
public class Category {
    @ApiModelProperty(value="类别对象在服务器数据库的id")
    private Integer id;

    @ApiModelProperty(value="类别对象在app端数据库的id")
    private Integer localId;

    @ApiModelProperty(value="该类别对象所属的用户的id")
    private Integer userId;

    @ApiModelProperty(value="类别名")
    private String name;

    @ApiModelProperty(value="标记该类别是收入类别还是支出类别,0为收入，1为支出")
    private Integer type;

    @ApiModelProperty(value="该记录在服务器端的上次修改时间")
    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", localId=").append(localId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", modified=").append(modified);
        sb.append("]");
        return sb.toString();
    }
}