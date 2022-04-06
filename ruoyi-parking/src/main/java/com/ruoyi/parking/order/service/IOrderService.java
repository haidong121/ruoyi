package com.ruoyi.parking.order.service;

import java.util.List;
import com.ruoyi.parking.order.domain.Order;

/**
 * 订单管理Service接口
 * 
 * @author zhuez
 * @date 2022-04-02
 */
public interface IOrderService 
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
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);
}
