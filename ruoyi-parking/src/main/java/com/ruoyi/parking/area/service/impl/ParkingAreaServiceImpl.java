package com.ruoyi.parking.area.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.area.mapper.ParkingAreaMapper;
import com.ruoyi.parking.area.domain.ParkingArea;
import com.ruoyi.parking.area.service.IParkingAreaService;

/**
 * 区域管理Service业务层处理
 * 
 * @author zhd
 * @date 2022-03-24
 */
@Service
public class ParkingAreaServiceImpl implements IParkingAreaService 
{
    @Autowired
    private ParkingAreaMapper parkingAreaMapper;

    /**
     * 查询区域管理
     * 
     * @param areaId 区域管理主键
     * @return 区域管理
     */
    @Override
    public ParkingArea selectParkingAreaByAreaId(Long areaId)
    {
        return parkingAreaMapper.selectParkingAreaByAreaId(areaId);
    }

    /**
     * 查询区域管理列表
     * 
     * @param parkingArea 区域管理
     * @return 区域管理
     */
    @Override
    public List<ParkingArea> selectParkingAreaList(ParkingArea parkingArea)
    {
        return parkingAreaMapper.selectParkingAreaList(parkingArea);
    }

    /**
     * 新增区域管理
     * 
     * @param parkingArea 区域管理
     * @return 结果
     */
    @Override
    public int insertParkingArea(ParkingArea parkingArea)
    {
        parkingArea.setCreateTime(DateUtils.getNowDate());
        return parkingAreaMapper.insertParkingArea(parkingArea);
    }

    /**
     * 修改区域管理
     * 
     * @param parkingArea 区域管理
     * @return 结果
     */
    @Override
    public int updateParkingArea(ParkingArea parkingArea)
    {
        parkingArea.setUpdateTime(DateUtils.getNowDate());
        return parkingAreaMapper.updateParkingArea(parkingArea);
    }

    /**
     * 批量删除区域管理
     * 
     * @param areaIds 需要删除的区域管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingAreaByAreaIds(Long[] areaIds)
    {
        return parkingAreaMapper.deleteParkingAreaByAreaIds(areaIds);
    }

    /**
     * 删除区域管理信息
     * 
     * @param areaId 区域管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingAreaByAreaId(Long areaId)
    {
        return parkingAreaMapper.deleteParkingAreaByAreaId(areaId);
    }
}
