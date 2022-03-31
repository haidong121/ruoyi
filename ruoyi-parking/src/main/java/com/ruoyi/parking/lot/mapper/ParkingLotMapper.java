package com.ruoyi.parking.lot.mapper;

import java.util.List;
import com.ruoyi.parking.lot.domain.ParkingLot;

/**
 * 车场管理Mapper接口
 * 
 * @author zhd
 * @date 2022-03-24
 */
public interface ParkingLotMapper 
{
    /**
     * 查询车场管理
     * 
     * @param lotId 车场管理主键
     * @return 车场管理
     */
    public ParkingLot selectParkingLotByLotId(Long lotId);

    /**
     * 查询车场管理列表
     * 
     * @param parkingLot 车场管理
     * @return 车场管理集合
     */
    public List<ParkingLot> selectParkingLotList(ParkingLot parkingLot);

    /**
     * 新增车场管理
     * 
     * @param parkingLot 车场管理
     * @return 结果
     */
    public int insertParkingLot(ParkingLot parkingLot);

    /**
     * 修改车场管理
     * 
     * @param parkingLot 车场管理
     * @return 结果
     */
    public int updateParkingLot(ParkingLot parkingLot);

    /**
     * 删除车场管理
     * 
     * @param lotId 车场管理主键
     * @return 结果
     */
    public int deleteParkingLotByLotId(Long lotId);

    /**
     * 批量删除车场管理
     * 
     * @param lotIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingLotByLotIds(Long[] lotIds);
}
