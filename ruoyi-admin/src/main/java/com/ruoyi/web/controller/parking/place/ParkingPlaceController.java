package com.ruoyi.web.controller.parking.place;

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
import com.ruoyi.parking.place.domain.ParkingPlace;
import com.ruoyi.parking.place.service.IParkingPlaceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车位管理Controller
 * 
 * @author zhd
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/system/place")
public class ParkingPlaceController extends BaseController
{
    @Autowired
    private IParkingPlaceService parkingPlaceService;

    /**
     * 查询车位管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:place:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkingPlace parkingPlace)
    {
        startPage();
        List<ParkingPlace> list = parkingPlaceService.selectParkingPlaceList(parkingPlace);
        return getDataTable(list);
    }

    /**
     * 导出车位管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:place:export')")
    @Log(title = "车位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParkingPlace parkingPlace)
    {
        List<ParkingPlace> list = parkingPlaceService.selectParkingPlaceList(parkingPlace);
        ExcelUtil<ParkingPlace> util = new ExcelUtil<ParkingPlace>(ParkingPlace.class);
        util.exportExcel(response, list, "车位管理数据");
    }

    /**
     * 获取车位管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:place:query')")
    @GetMapping(value = "/{placeId}")
    public AjaxResult getInfo(@PathVariable("placeId") Long placeId)
    {
        return AjaxResult.success(parkingPlaceService.selectParkingPlaceByPlaceId(placeId));
    }

    /**
     * 新增车位管理
     */
    @PreAuthorize("@ss.hasPermi('system:place:add')")
    @Log(title = "车位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkingPlace parkingPlace)
    {
        return toAjax(parkingPlaceService.insertParkingPlace(parkingPlace));
    }

    /**
     * 修改车位管理
     */
    @PreAuthorize("@ss.hasPermi('system:place:edit')")
    @Log(title = "车位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkingPlace parkingPlace)
    {
        return toAjax(parkingPlaceService.updateParkingPlace(parkingPlace));
    }

    /**
     * 删除车位管理
     */
    @PreAuthorize("@ss.hasPermi('system:place:remove')")
    @Log(title = "车位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{placeIds}")
    public AjaxResult remove(@PathVariable Long[] placeIds)
    {
        return toAjax(parkingPlaceService.deleteParkingPlaceByPlaceIds(placeIds));
    }
}
