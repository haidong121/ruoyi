package com.ruoyi.parking.place.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.place.mapper.ParkingPlaceMapper;
import com.ruoyi.parking.place.domain.ParkingPlace;
import com.ruoyi.parking.place.service.IParkingPlaceService;

/**
 * 车位管理Service业务层处理
 * 
 * @author zhd
 * @date 2022-03-25
 */
@Service
public class ParkingPlaceServiceImpl implements IParkingPlaceService 
{
    @Autowired
    private ParkingPlaceMapper parkingPlaceMapper;

    /**
     * 查询车位管理
     * 
     * @param placeId 车位管理主键
     * @return 车位管理
     */
    @Override
    public ParkingPlace selectParkingPlaceByPlaceId(Long placeId)
    {
        return parkingPlaceMapper.selectParkingPlaceByPlaceId(placeId);
    }

    /**
     * 查询车位管理列表
     * 
     * @param parkingPlace 车位管理
     * @return 车位管理
     */
    @Override
    public List<ParkingPlace> selectParkingPlaceList(ParkingPlace parkingPlace)
    {
        return parkingPlaceMapper.selectParkingPlaceList(parkingPlace);
    }

    /**
     * 新增车位管理
     * 
     * @param parkingPlace 车位管理
     * @return 结果
     */
    @Override
    public int insertParkingPlace(ParkingPlace parkingPlace)
    {
        parkingPlace.setCreateTime(DateUtils.getNowDate());
        return parkingPlaceMapper.insertParkingPlace(parkingPlace);
    }

    /**
     * 修改车位管理
     * 
     * @param parkingPlace 车位管理
     * @return 结果
     */
    @Override
    public int updateParkingPlace(ParkingPlace parkingPlace)
    {
        parkingPlace.setUpdateTime(DateUtils.getNowDate());
        return parkingPlaceMapper.updateParkingPlace(parkingPlace);
    }

    /**
     * 批量删除车位管理
     * 
     * @param placeIds 需要删除的车位管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingPlaceByPlaceIds(Long[] placeIds)
    {
        return parkingPlaceMapper.deleteParkingPlaceByPlaceIds(placeIds);
    }

    /**
     * 删除车位管理信息
     * 
     * @param placeId 车位管理主键
     * @return 结果
     */
    @Override
    public int deleteParkingPlaceByPlaceId(Long placeId)
    {
        return parkingPlaceMapper.deleteParkingPlaceByPlaceId(placeId);
    }
}
