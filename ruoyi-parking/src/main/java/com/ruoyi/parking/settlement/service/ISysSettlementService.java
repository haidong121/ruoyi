package com.ruoyi.parking.settlement.service;

import java.util.List;
import com.ruoyi.parking.settlement.domain.SysSettlement;

/**
 * 结算管理Service接口
 * 
 * @author zhuez
 * @date 2022-04-02
 */
public interface ISysSettlementService 
{
    /**
     * 查询结算管理
     * 
     * @param settlementId 结算管理主键
     * @return 结算管理
     */
    public SysSettlement selectSysSettlementBySettlementId(Long settlementId);

    /**
     * 查询结算管理列表
     * 
     * @param sysSettlement 结算管理
     * @return 结算管理集合
     */
    public List<SysSettlement> selectSysSettlementList(SysSettlement sysSettlement);

    /**
     * 新增结算管理
     * 
     * @param sysSettlement 结算管理
     * @return 结果
     */
    public int insertSysSettlement(SysSettlement sysSettlement);

    /**
     * 修改结算管理
     * 
     * @param sysSettlement 结算管理
     * @return 结果
     */
    public int updateSysSettlement(SysSettlement sysSettlement);

    /**
     * 批量删除结算管理
     * 
     * @param settlementIds 需要删除的结算管理主键集合
     * @return 结果
     */
    public int deleteSysSettlementBySettlementIds(Long[] settlementIds);

    /**
     * 删除结算管理信息
     * 
     * @param settlementId 结算管理主键
     * @return 结果
     */
    public int deleteSysSettlementBySettlementId(Long settlementId);
}
