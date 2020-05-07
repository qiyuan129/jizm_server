package com.example.jizm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="账单对象，一个Bill对象对应一条收支记录")
public class Bill {
    @ApiModelProperty(value="账单对象在服务器端数据库的id")
    private Integer id;

    @ApiModelProperty(value="账单对象在app端数据库的id")
    private Integer localId;

    @ApiModelProperty(value="该账单对象对应的账户")
    private Account account;

    @ApiModelProperty(value="")
    private Category category;

    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="标记该条账单是收入还是支出，0为支出，1为收入")
    private Integer type;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Date date;

    @ApiModelProperty(value="")
    private Double money;

    @ApiModelProperty(value="")
    private Date modified;

    private transient int state;

    private transient Date anchor;

    public Bill(){
    }

    public Bill(Integer localId, Integer accountId, Integer categoryId, Integer userId, Integer type, String name,
                Double money, int state) {
        this.localId = localId;
        this.account = new Account(accountId);
        this.category = new Category(categoryId);
        this.userId = userId;
        this.type = type;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", localId=" + localId +
                ", account=" + account +
                ", category=" + category +
                ", userId=" + userId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", money=" + money +
                ", modified=" + modified +
                ", state=" + state +
                ", anchor=" + anchor +
                '}';
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

}