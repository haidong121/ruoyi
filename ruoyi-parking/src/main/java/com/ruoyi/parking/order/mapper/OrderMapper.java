package com.ruoyi.parking.order.mapper;

import java.util.List;

import com.ruoyi.parking.lot.domain.ParkingLot;
import com.ruoyi.parking.order.domain.Order;

/**
 * 订单管理Mapper接口
 * 
 * @author zhuez
 * @date 2022-04-02
 */
public interface OrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    public Order selectOrderByOrderId(Long orderId);

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单管理
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    /**
     * 批量删除车场
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingLotByLotIds(Long[] orderIds);
    
    /**
     * 批量新增车场
     * 
     * @param parkingLotList 车场列表
     * @return 结果
     */
    public int batchParkingLot(List<ParkingLot> parkingLotList);
    

    /**
     * 通过订单管理主键删除车场信息
     * 
     * @param orderId 订单管理ID
     * @return 结果
     */
    public int deleteParkingLotByLotId(Long orderId);
}
