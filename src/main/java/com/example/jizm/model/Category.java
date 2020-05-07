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

    @ApiModelProperty(value="标记该类别是收入类别还是支出类别,0为支出，1为收入")
    private Integer type;

    @ApiModelProperty(value="该记录在服务器端的上次修改时间")
    private Date modified;

    private transient int state;

    private transient Date anchor;

    public Category(){
    }
    public Category(Integer id){
        this.localId=id;
    }

    public Category(Integer localId, Integer userId, String name, Integer type, int state) {
        this.localId = localId;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.state = state;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getAnchor() {
        return anchor;
    }

    public void setAnchor(Date anchor) {
        this.anchor = anchor;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", localId=" + localId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", modified=" + modified +
                ", state=" + state +
                ", anchor=" + anchor +
                '}';
    }
}