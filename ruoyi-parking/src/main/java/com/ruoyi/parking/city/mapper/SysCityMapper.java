package com.ruoyi.parking.city.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysCity;

/**
 * 城区管理Mapper接口
 * 
 * @author zhd
 * @date 2022-03-24
 */
public interface SysCityMapper 
{
    /**
     * 查询城区管理
     * 
     * @param cityId 城区管理主键
     * @return 城区管理
     */
    public SysCity selectSysCityByCityId(Long cityId);

    /**
     * 查询城区管理列表
     * 
     * @param sysCity 城区管理
     * @return 城区管理集合
     */
    public List<SysCity> selectSysCityList(SysCity sysCity);

    /**
     * 新增城区管理
     * 
     * @param sysCity 城区管理
     * @return 结果
     */
    public int insertSysCity(SysCity sysCity);

    /**
     * 修改城区管理
     * 
     * @param sysCity 城区管理
     * @return 结果
     */
    public int updateSysCity(SysCity sysCity);

    /**
     * 删除城区管理
     * 
     * @param cityId 城区管理主键
     * @return 结果
     */
    public int deleteSysCityByCityId(Long cityId);

    /**
     * 批量删除城区管理
     * 
     * @param cityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCityByCityIds(Long[] cityIds);
}
