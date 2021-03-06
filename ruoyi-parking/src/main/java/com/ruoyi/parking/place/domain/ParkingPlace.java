package com.ruoyi.parking.place.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ruoyi.parking.area.domain.ParkingArea;
import com.ruoyi.parking.lot.domain.ParkingLot;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车位管理对象 parking_place
 * 
 * @author zhd
 * @date 2022-03-31
 */
public class ParkingPlace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车位编号 */
    private Long placeId;

    /** 所属车场 */
    @Excel(name = "所属车场")
    private Long lotId;

    private ParkingLot lot;

    /** 所属区域 */
    @Excel(name = "所属区域")
    private Long areaId;

    private ParkingArea area;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long equipmentType;

    /** 设备SN码 */
    @Excel(name = "设备SN码")
    private String equipmentSncode;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bindDate;

    /** 泊位类型 */
    @Excel(name = "泊位类型")
    private String placeType;

    /** 泊位属性 */
    @Excel(name = "泊位属性")
    private String placeProperty;

    public void setPlaceId(Long placeId) 
    {
        this.placeId = placeId;
    }

    public Long getPlaceId() 
    {
        return placeId;
    }
    public void setLotId(Long lotId) 
    {
        this.lotId = lotId;
    }

    public Long getLotId() 
    {
        return lotId;
    }
    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setEquipmentType(Long equipmentType) 
    {
        this.equipmentType = equipmentType;
    }

    public Long getEquipmentType() 
    {
        return equipmentType;
    }
    public void setEquipmentSncode(String equipmentSncode) 
    {
        this.equipmentSncode = equipmentSncode;
    }

    public String getEquipmentSncode() 
    {
        return equipmentSncode;
    }
    public void setBindDate(Date bindDate) 
    {
        this.bindDate = bindDate;
    }

    public Date getBindDate() 
    {
        return bindDate;
    }
    public void setPlaceType(String placeType) 
    {
        this.placeType = placeType;
    }

    public String getPlaceType() 
    {
        return placeType;
    }
    public void setPlaceProperty(String placeProperty) 
    {
        this.placeProperty = placeProperty;
    }

    public String getPlaceProperty() 
    {
        return placeProperty;
    }

    public ParkingLot getLot()
    {
        return lot;
    }

    public void setLot(ParkingLot lot)
    {
        this.lot = lot;
    }

    public ParkingArea getArea()
    {
        return area;
    }

    public void setArea(ParkingArea area) { this.area = area; }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("placeId", getPlaceId())
            .append("lotId", getLotId())
            .append("areaId", getAreaId())
            .append("equipmentType", getEquipmentType())
            .append("equipmentSncode", getEquipmentSncode())
            .append("bindDate", getBindDate())
            .append("placeType", getPlaceType())
            .append("placeProperty", getPlaceProperty())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lot", getLot())
            .append("area", getArea())
            .toString();
    }
}
