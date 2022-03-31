package com.ruoyi.parking.place.mapper;

import java.util.List;
import com.ruoyi.parking.place.domain.ParkingPlace;

/**
 * 车位管理Mapper接口
 * 
 * @author zhd
 * @date 2022-03-31
 */
public interface ParkingPlaceMapper 
{
    /**
     * 查询车位管理
     * 
     * @param placeId 车位管理主键
     * @return 车位管理
     */
    public ParkingPlace selectParkingPlaceByPlaceId(Long placeId);

    /**
     * 查询车位管理列表
     * 
     * @param parkingPlace 车位管理
     * @return 车位管理集合
     */
    public List<ParkingPlace> selectParkingPlaceList(ParkingPlace parkingPlace);

    /**
     * 新增车位管理
     * 
     * @param parkingPlace 车位管理
     * @return 结果
     */
    public int insertParkingPlace(ParkingPlace parkingPlace);

    /**
     * 修改车位管理
     * 
     * @param parkingPlace 车位管理
     * @return 结果
     */
    public int updateParkingPlace(ParkingPlace parkingPlace);

    /**
     * 删除车位管理
     * 
     * @param placeId 车位管理主键
     * @return 结果
     */
    public int deleteParkingPlaceByPlaceId(Long placeId);

    /**
     * 批量删除车位管理
     * 
     * @param placeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingPlaceByPlaceIds(Long[] placeIds);
}
