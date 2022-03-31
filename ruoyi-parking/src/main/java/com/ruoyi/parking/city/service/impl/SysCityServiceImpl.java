package com.ruoyi.parking.city.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.city.mapper.SysCityMapper;
import com.ruoyi.common.core.domain.entity.SysCity;
import com.ruoyi.parking.city.service.ISysCityService;

/**
 * 城区管理Service业务层处理
 * 
 * @author zhd
 * @date 2022-03-24
 */
@Service
public class SysCityServiceImpl implements ISysCityService 
{
    @Autowired
    private SysCityMapper sysCityMapper;

    /**
     * 查询城区管理
     * 
     * @param cityId 城区管理主键
     * @return 城区管理
     */
    @Override
    public SysCity selectSysCityByCityId(Long cityId)
    {
        return sysCityMapper.selectSysCityByCityId(cityId);
    }

    /**
     * 查询城区管理列表
     * 
     * @param sysCity 城区管理
     * @return 城区管理
     */
    @Override
    public List<SysCity> selectSysCityList(SysCity sysCity)
    {
        return sysCityMapper.selectSysCityList(sysCity);
    }

    /**
     * 新增城区管理
     * 
     * @param sysCity 城区管理
     * @return 结果
     */
    @Override
    public int insertSysCity(SysCity sysCity)
    {
        sysCity.setCreateTime(DateUtils.getNowDate());
        return sysCityMapper.insertSysCity(sysCity);
    }

    /**
     * 修改城区管理
     * 
     * @param sysCity 城区管理
     * @return 结果
     */
    @Override
    public int updateSysCity(SysCity sysCity)
    {
        sysCity.setUpdateTime(DateUtils.getNowDate());
        return sysCityMapper.updateSysCity(sysCity);
    }

    /**
     * 批量删除城区管理
     * 
     * @param cityIds 需要删除的城区管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCityByCityIds(Long[] cityIds)
    {
        return sysCityMapper.deleteSysCityByCityIds(cityIds);
    }

    /**
     * 删除城区管理信息
     * 
     * @param cityId 城区管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCityByCityId(Long cityId)
    {
        return sysCityMapper.deleteSysCityByCityId(cityId);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param citys 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildCityTreeSelect(List<SysCity> citys) {
        List<SysCity> cityTrees = buildCityTree(citys);
        return cityTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param citys 部门列表
     * @return 树结构列表
     */
    @Override
    public List<SysCity> buildCityTree(List<SysCity> citys) {
        List<SysCity> returnList = new ArrayList<SysCity>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysCity city : citys)
        {
            tempList.add(city.getCityId());
        }
        for (SysCity city : citys)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(city.getParentId()))
            {
                recursionFn(citys, city);
                returnList.add(city);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = citys;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysCity> list, SysCity t)
    {
        // 得到子节点列表
        List<SysCity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysCity tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysCity> getChildList(List<SysCity> list, SysCity t)
    {
        List<SysCity> tlist = new ArrayList<SysCity>();
        Iterator<SysCity> it = list.iterator();
        while (it.hasNext())
        {
            SysCity n = (SysCity) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getCityId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysCity> list, SysCity t)
    {
        return getChildList(list, t).size() > 0;
    }
}
