package com.example.jizm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="com-example-jizm-model-Account")
public class Account {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private Integer localId;

    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Double money;

    @ApiModelProperty(value="")
    private Date modified;

    private transient int state;

    private transient Date anchor;

    public Account() {
    }

    public Account(Integer localId, Integer userId, String name, Double money, int state) {
        this.localId = localId;
        this.userId = userId;
        this.name = name;
        this.money = money;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", localId=").append(localId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", money=").append(money);
        sb.append(", modified=").append(modified);
        sb.append("]");
        return sb.toString();
    }
}