package com.ruoyi.parking.area.domain;

import com.ruoyi.common.core.domain.entity.SysCity;
import com.ruoyi.parking.lot.domain.ParkingLot;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 区域对象 parking_area
 * 
 * @author zhd
 * @date 2022-04-01
 */
public class ParkingArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域id */
    private Long areaId;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String areaName;

    /** 车场id */
    @Excel(name = "车场id")
    private String lotId;

    private ParkingLot lot;

    /** 城区id */
    @Excel(name = "城区id")
    private Long cityId;

    /** 泊位数量 */
    @Excel(name = "泊位数量")
    private Long placeNum;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long orderNum;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setLotId(String lotId) 
    {
        this.lotId = lotId;
    }

    public String getLotId() 
    {
        return lotId;
    }
    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setPlaceNum(Long placeNum) 
    {
        this.placeNum = placeNum;
    }

    public Long getPlaceNum() 
    {
        return placeNum;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public ParkingLot getLot()
    {
        return lot;
    }

    public void setLot(ParkingLot lot)
    {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaId", getAreaId())
            .append("areaName", getAreaName())
            .append("lotId", getLotId())
            .append("cityId", getCityId())
            .append("placeNum", getPlaceNum())
            .append("orderNum", getOrderNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("lot",getLot())
            .toString();
    }
}
