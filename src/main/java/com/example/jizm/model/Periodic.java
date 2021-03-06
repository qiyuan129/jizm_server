package com.example.jizm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="周期时间类")
public class Periodic {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private Integer localId;

    @ApiModelProperty(value="")
    private Integer accountId;

    @ApiModelProperty(value="")
    private Integer categoryId;

    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="")
    private Integer type;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Integer cycle;

    @ApiModelProperty(value="")
    private Date start;

    @ApiModelProperty(value="")
    private Date end;

    @ApiModelProperty(value="")
    private Double money;

    @ApiModelProperty(value="")
    private Date modified;

    private transient int state;

    private transient Date anchor;

    public Periodic(){
    }
    public Periodic(Integer id){
        this.localId=id;
    }

    public Periodic(Integer localId, Integer accountId, Integer categoryId, Integer userId, Integer type, String name,
                    int state, Date anchor) {
        this.localId = localId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.state = state;
        this.anchor = anchor;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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
        return "Periodic{" +
                "id=" + id +
                ", localId=" + localId +
                ", accountId=" + accountId +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", cycle=" + cycle +
                ", start=" + start +
                ", end=" + end +
                ", money=" + money +
                ", modified=" + modified +
                ", state=" + state +
                ", anchor=" + anchor +
                '}';
    }
}