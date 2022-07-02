package com.yzx.mall.model.request;

import javax.validation.constraints.*;

public class AddCategoryReq {

    @NotEmpty(message = "NO Category Name!")
    @NotBlank(message = "NO Blank!")
    @Size(min = 2, max = 5)
    private String name;

    @NotNull(message = "NO type!")
    @Max(3)
    private Integer type;

    @NotNull(message = "NO PID!")
    private Integer parentId;

    @NotNull(message = "NO order!")
    private Integer orderNum;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
