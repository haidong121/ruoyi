package com.ruoyi.web.controller.parking.city;

import java.util.List;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.SysCity;
import com.ruoyi.parking.city.service.ISysCityService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 城区管理Controller
 * 
 * @author zhd
 * @date 2022-03-24
 */
@RestController
@RequestMapping("/system/city")
public class SysCityController extends BaseController
{
    @Autowired
    private ISysCityService sysCityService;

    /**
     * 查询城区管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:list')")
    @GetMapping("/list")
    public AjaxResult list(SysCity sysCity)
    {
        List<SysCity> list = sysCityService.selectSysCityList(sysCity);
        return AjaxResult.success(list);
    }

    /**
     * 导出城区管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:export')")
    @Log(title = "城区管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCity sysCity)
    {
        List<SysCity> list = sysCityService.selectSysCityList(sysCity);
        ExcelUtil<SysCity> util = new ExcelUtil<SysCity>(SysCity.class);
        util.exportExcel(response, list, "城区管理数据");
    }

    /**
     * 获取城区管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:city:query')")
    @GetMapping(value = "/{cityId}")
    public AjaxResult getInfo(@PathVariable("cityId") Long cityId)
    {
        return AjaxResult.success(sysCityService.selectSysCityByCityId(cityId));
    }

    /**
     * 新增城区管理
     */
    @PreAuthorize("@ss.hasPermi('system:city:add')")
    @Log(title = "城区管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCity sysCity)
    {
        return toAjax(sysCityService.insertSysCity(sysCity));
    }

    /**
     * 修改城区管理
     */
    @PreAuthorize("@ss.hasPermi('system:city:edit')")
    @Log(title = "城区管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCity sysCity)
    {
        return toAjax(sysCityService.updateSysCity(sysCity));
    }

    /**
     * 删除城区管理
     */
    @PreAuthorize("@ss.hasPermi('system:city:remove')")
    @Log(title = "城区管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityIds}")
    public AjaxResult remove(@PathVariable Long[] cityIds)
    {
        return toAjax(sysCityService.deleteSysCityByCityIds(cityIds));
    }

    /**
     * 获取城区下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysCity city)
    {
        System.out.println("下拉列表");
        List<SysCity> citys = sysCityService.selectSysCityList(city);
        return AjaxResult.success(sysCityService.buildCityTreeSelect(citys));
    }
}
