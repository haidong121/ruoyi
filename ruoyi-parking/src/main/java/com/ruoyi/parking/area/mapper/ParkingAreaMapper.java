package com.ruoyi.parking.area.mapper;

import java.util.List;
import com.ruoyi.parking.area.domain.ParkingArea;

/**
 * 区域管理Mapper接口
 * 
 * @author zhd
 * @date 2022-03-24
 */
public interface ParkingAreaMapper 
{
    /**
     * 查询区域管理
     * 
     * @param areaId 区域管理主键
     * @return 区域管理
     */
    public ParkingArea selectParkingAreaByAreaId(Long areaId);

    /**
     * 查询区域管理列表
     * 
     * @param parkingArea 区域管理
     * @return 区域管理集合
     */
    public List<ParkingArea> selectParkingAreaList(ParkingArea parkingArea);

    /**
     * 新增区域管理
     * 
     * @param parkingArea 区域管理
     * @return 结果
     */
    public int insertParkingArea(ParkingArea parkingArea);

    /**
     * 修改区域管理
     * 
     * @param parkingArea 区域管理
     * @return 结果
     */
    public int updateParkingArea(ParkingArea parkingArea);

    /**
     * 删除区域管理
     * 
     * @param areaId 区域管理主键
     * @return 结果
     */
    public int deleteParkingAreaByAreaId(Long areaId);

    /**
     * 批量删除区域管理
     * 
     * @param areaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingAreaByAreaIds(Long[] areaIds);
}
