package com.ruoyi.parking.order.domain;

import java.util.List;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.parking.lot.domain.ParkingLot;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 order
 *
 * @author zhuez
 * @date 2022-04-02
 */
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 城市id
     */
    @Excel(name = "城市id")
    private Long cityId;

    /**
     * 车场id
     */
    @Excel(name = "车场id")
    private Long lotId;

    /**
     * 车场名称
     **/
    @Excel(name = "车场名称")
    private Long lodName;

    /**
     * 泊位id
     */
    @Excel(name = "泊位id")
    private Long placeId;

    /**
     * 车辆id
     */
    @Excel(name = "车辆id")
    private Long carId;

    /**
     * 驶入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "驶入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 驶出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "驶出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 停车时长
     */
    @Excel(name = "停车时长")
    private Long duration;

    /**
     * 删除标志（0未完成 1已支付 2未支付）
     */
    @Excel(name = "删除标志", readConverterExp = "0=未完成,1=已支付,2=未支付")
    private String orderStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 车场
     */
    private ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * 车场信息
     */
    private List<ParkingLot> parkingLotList;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("cityId", getCityId())
                .append("lotId", getLotId())
                .append("placeId", getPlaceId())
                .append("carId", getCarId())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("duration", getDuration())
                .append("orderStatus", getOrderStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("parkingLotList", getParkingLotList())
                .append("parkingLot",getParkingLot())
                .toString();
    }
}
