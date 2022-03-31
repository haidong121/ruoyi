package com.ruoyi.parking.lot.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysCity;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车场管理对象 parking_lot
 * 
 * @author zhd
 * @date 2022-03-24
 */
public class ParkingLot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车场id */
    private Long lotId;

    /** 车场名称 */
    @Excel(name = "车场名称")
    private String lotName;

    /** 车场简称 */
    @Excel(name = "车场简称")
    private String shortName;

    /** 城区id */
    @Excel(name = "城区id")
    private Long cityId;

    private SysCity city;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 泊位数量 */
    @Excel(name = "泊位数量")
    private Long placeNum;

    /** 车场类型（0路侧停车场,1商场,2医院） */
    @Excel(name = "车场类型", readConverterExp = "0=路侧停车场,1商场,2医院")
    private Long lotStyle;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long orderNum;

    /** 建成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "建成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 车场状态（0正常 1停用） */
    @Excel(name = "车场状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setLotId(Long lotId) 
    {
        this.lotId = lotId;
    }

    public Long getLotId() 
    {
        return lotId;
    }
    public void setLotName(String lotName) 
    {
        this.lotName = lotName;
    }

    public String getLotName() 
    {
        return lotName;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPlaceNum(Long placeNum) 
    {
        this.placeNum = placeNum;
    }

    public Long getPlaceNum() 
    {
        return placeNum;
    }
    public void setLotStyle(Long lotStyle) 
    {
        this.lotStyle = lotStyle;
    }

    public Long getLotStyle() 
    {
        return lotStyle;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setBuildTime(Date buildTime) 
    {
        this.buildTime = buildTime;
    }

    public Date getBuildTime() 
    {
        return buildTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public SysCity getCity()
    {
        return city;
    }

    public void setcity(SysCity city)
    {
        this.city = city;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lotId", getLotId())
            .append("lotName", getLotName())
            .append("shortName", getShortName())
            .append("cityId", getCityId())
            .append("address", getAddress())
            .append("placeNum", getPlaceNum())
            .append("lotStyle", getLotStyle())
            .append("orderNum", getOrderNum())
            .append("buildTime", getBuildTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("city", getCity())
            .toString();
    }
}
