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

    @ApiModelProperty(value="")
    private Account account;

    @ApiModelProperty(value="")
    private Category category;

    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="")
    private Integer type;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Date date;

    @ApiModelProperty(value="")
    private Double money;

    @ApiModelProperty(value="")
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", localId=").append(localId);
        sb.append(", accountId=").append(account.getId());
        sb.append(", categoryId=").append(category.getId());
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", date=").append(date);
        sb.append(", money=").append(money);
        sb.append(", modified=").append(modified);
        sb.append("]");
        return sb.toString();
    }
}