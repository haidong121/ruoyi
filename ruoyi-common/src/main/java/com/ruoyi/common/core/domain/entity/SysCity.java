package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 城区管理对象 sys_city
 * 
 * @author zhd
 * @date 2022-03-24
 */
public class SysCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 城区id */
    private Long cityId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 显示顺序 */
    private String orderNum;

    /** 城区名称 */
    @Excel(name = "城区名称")
    private String cityName;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 子部门 */
    private List<SysCity> children = new ArrayList<SysCity>();

    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getCityId()
    {
        return cityId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId() { return parentId; }

    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() { return cityName; }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public void setChildren(List<SysCity> children) { this.children = children; }

    public List<SysCity> getChildren() { return children; }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cityId", getCityId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("cityName", getCityName())
            .append("orderNum", getOrderNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
