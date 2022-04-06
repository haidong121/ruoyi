package com.ruoyi.parking.settlement.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.parking.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.parking.settlement.mapper.SysSettlementMapper;
import com.ruoyi.parking.settlement.domain.SysSettlement;
import com.ruoyi.parking.settlement.service.ISysSettlementService;


/**
 * 结算管理Service业务层处理
 * 
 * @author zhuez
 * @date 2022-04-02
 */
@Service
public class SysSettlementServiceImpl implements ISysSettlementService 
{
    @Autowired
    private SysSettlementMapper sysSettlementMapper;

    /**
     * 查询结算管理
     * 
     * @param settlementId 结算管理主键
     * @return 结算管理
     */
    @Override
    public SysSettlement selectSysSettlementBySettlementId(Long settlementId)
    {
        return sysSettlementMapper.selectSysSettlementBySettlementId(settlementId);
    }

    /**
     * 查询结算管理列表
     * 
     * @param sysSettlement 结算管理
     * @return 结算管理
     */
    @Override
    public List<SysSettlement> selectSysSettlementList(SysSettlement sysSettlement)
    {
        return sysSettlementMapper.selectSysSettlementList(sysSettlement);
    }

    /**
     * 新增结算管理
     * 
     * @param sysSettlement 结算管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysSettlement(SysSettlement sysSettlement)
    {
        sysSettlement.setCreateTime(DateUtils.getNowDate());
        int rows = sysSettlementMapper.insertSysSettlement(sysSettlement);
        insertOrder(sysSettlement);
        return rows;
    }

    /**
     * 修改结算管理
     * 
     * @param sysSettlement 结算管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysSettlement(SysSettlement sysSettlement)
    {
        sysSettlement.setUpdateTime(DateUtils.getNowDate());
        sysSettlementMapper.deleteOrderByOrderId(sysSettlement.getSettlementId());
        insertOrder(sysSettlement);
        return sysSettlementMapper.updateSysSettlement(sysSettlement);
    }

    /**
     * 批量删除结算管理
     * 
     * @param settlementIds 需要删除的结算管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSettlementBySettlementIds(Long[] settlementIds)
    {
        sysSettlementMapper.deleteOrderByOrderIds(settlementIds);
        return sysSettlementMapper.deleteSysSettlementBySettlementIds(settlementIds);
    }

    /**
     * 删除结算管理信息
     * 
     * @param settlementId 结算管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSettlementBySettlementId(Long settlementId)
    {
        sysSettlementMapper.deleteOrderByOrderId(settlementId);
        return sysSettlementMapper.deleteSysSettlementBySettlementId(settlementId);
    }

    /**
     * 新增订单管理信息
     * 
     * @param sysSettlement 结算管理对象
     */
    public void insertOrder(SysSettlement sysSettlement)
    {
        List<Order> orderList = sysSettlement.getOrderList();
        Long settlementId = sysSettlement.getSettlementId();
        if (StringUtils.isNotNull(orderList))
        {
            List<Order> list = new ArrayList<Order>();
            for (Order order : orderList)
            {
                order.setOrderId(settlementId);
                list.add(order);
            }
            if (list.size() > 0)
            {
                sysSettlementMapper.batchOrder(list);
            }
        }
    }
}
