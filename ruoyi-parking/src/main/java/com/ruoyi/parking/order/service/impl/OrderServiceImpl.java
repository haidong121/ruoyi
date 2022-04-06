package com.ruoyi.parking.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.parking.lot.domain.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.parking.order.mapper.OrderMapper;
import com.ruoyi.parking.order.domain.Order;
import com.ruoyi.parking.order.service.IOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author zhuez
 * @date 2022-04-02
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    @Override
    public Order selectOrderByOrderId(Long orderId)
    {
        return orderMapper.selectOrderByOrderId(orderId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        int rows = orderMapper.insertOrder(order);
        insertParkingLot(order);
        return rows;
    }

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateOrder(Order order)
    {
        order.setUpdateTime(DateUtils.getNowDate());
        orderMapper.deleteParkingLotByLotId(order.getOrderId());
        insertParkingLot(order);
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderByOrderIds(Long[] orderIds)
    {
        orderMapper.deleteParkingLotByLotIds(orderIds);
        return orderMapper.deleteOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderByOrderId(Long orderId)
    {
        orderMapper.deleteParkingLotByLotId(orderId);
        return orderMapper.deleteOrderByOrderId(orderId);
    }

    /**
     * 新增车场信息
     * 
     * @param order 订单管理对象
     */
    public void insertParkingLot(Order order)
    {
        List<ParkingLot> parkingLotList = order.getParkingLotList();
        Long orderId = order.getOrderId();
        if (StringUtils.isNotNull(parkingLotList))
        {
            List<ParkingLot> list = new ArrayList<ParkingLot>();
            for (ParkingLot parkingLot : parkingLotList)
            {
                parkingLot.setLotId(orderId);
                list.add(parkingLot);
            }
            if (list.size() > 0)
            {
                orderMapper.batchParkingLot(list);
            }
        }
    }
}
