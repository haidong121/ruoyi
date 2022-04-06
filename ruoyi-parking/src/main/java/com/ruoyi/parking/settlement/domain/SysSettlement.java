package com.ruoyi.parking.settlement.domain;

import java.util.List;

import com.ruoyi.parking.order.domain.Order;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 结算管理对象 sys_settlement
 * 
 * @author zhuez
 * @date 2022-04-02
 */
public class SysSettlement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结算方编号 */
    private Long settlementId;

    /** 结算方名称 */
    @Excel(name = "结算方名称")
    private String settlementName;

    /** 所属城区 */
    @Excel(name = "所属城区")
    private Long cityId;

    /** 结算账期 */
    @Excel(name = "结算账期")
    private Long settlementPeriod;

    /** 营业执照编号 */
    @Excel(name = "营业执照编号")
    private Long businessLicense;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 银行开户名 */
    @Excel(name = "银行开户名")
    private String accountName;

    /** 开户行 */
    @Excel(name = "开户行")
    private String accountBank;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 订单管理信息 */
    private List<Order> orderList;

    public void setSettlementId(Long settlementId) 
    {
        this.settlementId = settlementId;
    }

    public Long getSettlementId() 
    {
        return settlementId;
    }
    public void setSettlementName(String settlementName) 
    {
        this.settlementName = settlementName;
    }

    public String getSettlementName() 
    {
        return settlementName;
    }
    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setSettlementPeriod(Long settlementPeriod) 
    {
        this.settlementPeriod = settlementPeriod;
    }

    public Long getSettlementPeriod() 
    {
        return settlementPeriod;
    }
    public void setBusinessLicense(Long businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public Long getBusinessLicense() 
    {
        return businessLicense;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountBank(String accountBank) 
    {
        this.accountBank = accountBank;
    }

    public String getAccountBank() 
    {
        return accountBank;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public List<Order> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(List<Order> orderList)
    {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("settlementId", getSettlementId())
            .append("settlementName", getSettlementName())
            .append("cityId", getCityId())
            .append("settlementPeriod", getSettlementPeriod())
            .append("businessLicense", getBusinessLicense())
            .append("address", getAddress())
            .append("accountName", getAccountName())
            .append("accountBank", getAccountBank())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("orderList", getOrderList())
            .toString();
    }
}
