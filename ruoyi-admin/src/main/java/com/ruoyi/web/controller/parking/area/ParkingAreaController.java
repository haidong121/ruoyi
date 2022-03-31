package com.ruoyi.web.controller.parking.area;

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
import com.ruoyi.parking.area.domain.ParkingArea;
import com.ruoyi.parking.area.service.IParkingAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 区域管理Controller
 * 
 * @author zhd
 * @date 2022-03-24
 */
@RestController
@RequestMapping("/system/area")
public class ParkingAreaController extends BaseController
{
    @Autowired
    private IParkingAreaService parkingAreaService;

    /**
     * 查询区域管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkingArea parkingArea)
    {
        startPage();
        List<ParkingArea> list = parkingAreaService.selectParkingAreaList(parkingArea);
        return getDataTable(list);
    }

    /**
     * 导出区域管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:export')")
    @Log(title = "区域管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParkingArea parkingArea)
    {
        List<ParkingArea> list = parkingAreaService.selectParkingAreaList(parkingArea);
        ExcelUtil<ParkingArea> util = new ExcelUtil<ParkingArea>(ParkingArea.class);
        util.exportExcel(response, list, "区域管理数据");
    }

    /**
     * 获取区域管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return AjaxResult.success(parkingAreaService.selectParkingAreaByAreaId(areaId));
    }

    /**
     * 新增区域管理
     */
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "区域管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkingArea parkingArea)
    {
        return toAjax(parkingAreaService.insertParkingArea(parkingArea));
    }

    /**
     * 修改区域管理
     */
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "区域管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkingArea parkingArea)
    {
        return toAjax(parkingAreaService.updateParkingArea(parkingArea));
    }

    /**
     * 删除区域管理
     */
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "区域管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(parkingAreaService.deleteParkingAreaByAreaIds(areaIds));
    }
}
