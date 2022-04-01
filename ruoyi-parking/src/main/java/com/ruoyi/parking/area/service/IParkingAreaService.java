package com.ruoyi.parking.area.service;

import java.util.List;
import com.ruoyi.parking.area.domain.ParkingArea;

/**
 * 区域Service接口
 * 
 * @author zhd
 * @date 2022-04-01
 */
public interface IParkingAreaService 
{
    /**
     * 查询区域
     * 
     * @param areaId 区域主键
     * @return 区域
     */
    public ParkingArea selectParkingAreaByAreaId(Long areaId);

    /**
     * 查询区域列表
     * 
     * @param parkingArea 区域
     * @return 区域集合
     */
    public List<ParkingArea> selectParkingAreaList(ParkingArea parkingArea);

    /**
     * 查询区域列表
     *
     * @param lotId 区域
     * @return 区域集合
     */
    public List<ParkingArea> selectParkingAreaByLotId(Long lotId);

    /**
     * 新增区域
     * 
     * @param parkingArea 区域
     * @return 结果
     */
    public int insertParkingArea(ParkingArea parkingArea);

    /**
     * 修改区域
     * 
     * @param parkingArea 区域
     * @return 结果
     */
    public int updateParkingArea(ParkingArea parkingArea);

    /**
     * 批量删除区域
     * 
     * @param areaIds 需要删除的区域主键集合
     * @return 结果
     */
    public int deleteParkingAreaByAreaIds(Long[] areaIds);

    /**
     * 删除区域信息
     * 
     * @param areaId 区域主键
     * @return 结果
     */
    public int deleteParkingAreaByAreaId(Long areaId);
}
