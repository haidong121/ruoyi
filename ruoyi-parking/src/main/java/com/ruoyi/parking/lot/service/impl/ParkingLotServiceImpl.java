package com.ruoyi.parking.lot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.lot.mapper.ParkingLotMapper;
import com.ruoyi.parking.lot.domain.ParkingLot;
import com.ruoyi.parking.lot.service.IParkingLotService;

/**
 * 车场管理Service业务层处理
 * 
 * @author zhd
 * @date 2022-03-24
 */
@Service
public class ParkingLotServiceImpl implements IParkingLotService 
{
    @Autowired
    private ParkingLotMapper parkingLotMapper;

    /**
     * 查询车场管理
     * 
     * @param lotId 车场管理主键
     * @return 车场管理
     */
    @Override
    public ParkingLot selectParkingLotByLotId(Long lotId)
    {
        return parkingLotMapper.selectParkingLotByLotId(lotId);
    }

    /**
     * 查询车场管理列表
     * 
     * @param parkingLot 车场管理
     * @return 车场管理
     */
    @Override
    public List<ParkingLot> selectParkingLotList(ParkingLot parkingLot)
    {
        return parkingLotMapper.selectParkingLotList(parkingLot);
    }

    /**
     * 新增车场管理
     * 
     * @param parkingLot 车场管理
     * @return 结果
     */
    @Override
    public int insertParkingLot(ParkingLot parkingLot)
    {
        parkingLot.setCreateTime(DateUtils.getNowDate());
        return parkingLotMapper.insertParkingLot(parkingLot);
    }

    /**
     * 修改车场管理
     * 
     * @param parkingLot 车场管理
     * @return 结果
     */
    @Override
    public int updateParkingLot(ParkingLot parkingLot)
    {
        parkingLot.setUpdateTime(DateUtils.getNowDate());
        return parkingLotMapper.updateParkingLot(parkingLot);
    }

    /**
     * 批量删除车场管理
     * 
     * @param lotIds 需要删除的车场管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingLotByLotIds(Long[] lotIds)
    {
        return parkingLotMapper.deleteParkingLotByLotIds(lotIds);
    }

    /**
     * 删除车场管理信息
     * 
     * @param lotId 车场管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingLotByLotId(Long lotId)
    {
        return parkingLotMapper.deleteParkingLotByLotId(lotId);
    }
}
