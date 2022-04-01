package com.ruoyi.parking.area.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.area.mapper.ParkingAreaMapper;
import com.ruoyi.parking.area.domain.ParkingArea;
import com.ruoyi.parking.area.service.IParkingAreaService;

/**
 * 区域Service业务层处理
 * 
 * @author zhd
 * @date 2022-04-01
 */
@Service
public class ParkingAreaServiceImpl implements IParkingAreaService 
{
    @Autowired
    private ParkingAreaMapper parkingAreaMapper;

    /**
     * 查询区域
     * 
     * @param areaId 区域主键
     * @return 区域
     */
    @Override
    public ParkingArea selectParkingAreaByAreaId(Long areaId)
    {
        return parkingAreaMapper.selectParkingAreaByAreaId(areaId);
    }

    /**
     * 查询区域列表
     * 
     * @param parkingArea 区域
     * @return 区域
     */
    @Override
    public List<ParkingArea> selectParkingAreaList(ParkingArea parkingArea)
    {
        return parkingAreaMapper.selectParkingAreaList(parkingArea);
    }

    /**
     * 查询区域列表
     *
     * @param lotId 区域
     * @return 区域
     */
    @Override
    public List<ParkingArea> selectParkingAreaByLotId(Long lotId) {
        return parkingAreaMapper.selectParkingAreaByLotId(lotId);
    }

    /**
     * 新增区域
     * 
     * @param parkingArea 区域
     * @return 结果
     */
    @Override
    public int insertParkingArea(ParkingArea parkingArea)
    {
        parkingArea.setCreateTime(DateUtils.getNowDate());
        return parkingAreaMapper.insertParkingArea(parkingArea);
    }

    /**
     * 修改区域
     * 
     * @param parkingArea 区域
     * @return 结果
     */
    @Override
    public int updateParkingArea(ParkingArea parkingArea)
    {
        parkingArea.setUpdateTime(DateUtils.getNowDate());
        return parkingAreaMapper.updateParkingArea(parkingArea);
    }

    /**
     * 批量删除区域
     * 
     * @param areaIds 需要删除的区域主键
     * @return 结果
     */
    @Override
    public int deleteParkingAreaByAreaIds(Long[] areaIds)
    {
        return parkingAreaMapper.deleteParkingAreaByAreaIds(areaIds);
    }

    /**
     * 删除区域信息
     * 
     * @param areaId 区域主键
     * @return 结果
     */
    @Override
    public int deleteParkingAreaByAreaId(Long areaId)
    {
        return parkingAreaMapper.deleteParkingAreaByAreaId(areaId);
    }
}
