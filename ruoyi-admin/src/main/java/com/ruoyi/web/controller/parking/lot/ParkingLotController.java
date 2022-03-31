package com.ruoyi.web.controller.parking.lot;

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
import com.ruoyi.parking.lot.domain.ParkingLot;
import com.ruoyi.parking.lot.service.IParkingLotService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车场管理Controller
 * 
 * @author zhd
 * @date 2022-03-24
 */
@RestController
@RequestMapping("/system/lot")
public class ParkingLotController extends BaseController
{
    @Autowired
    private IParkingLotService parkingLotService;

    /**
     * 查询车场管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:lot:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkingLot parkingLot)
    {
        startPage();
        List<ParkingLot> list = parkingLotService.selectParkingLotList(parkingLot);
        return getDataTable(list);
    }

    /**
     * 导出车场管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:lot:export')")
    @Log(title = "车场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParkingLot parkingLot)
    {
        List<ParkingLot> list = parkingLotService.selectParkingLotList(parkingLot);
        ExcelUtil<ParkingLot> util = new ExcelUtil<ParkingLot>(ParkingLot.class);
        util.exportExcel(response, list, "车场管理数据");
    }

    /**
     * 获取车场管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lot:query')")
    @GetMapping(value = "/{lotId}")
    public AjaxResult getInfo(@PathVariable("lotId") Long lotId)
    {
        return AjaxResult.success(parkingLotService.selectParkingLotByLotId(lotId));
    }

    /**
     * 新增车场管理
     */
    @PreAuthorize("@ss.hasPermi('system:lot:add')")
    @Log(title = "车场管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkingLot parkingLot)
    {
        return toAjax(parkingLotService.insertParkingLot(parkingLot));
    }

    /**
     * 修改车场管理
     */
    @PreAuthorize("@ss.hasPermi('system:lot:edit')")
    @Log(title = "车场管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkingLot parkingLot)
    {
        return toAjax(parkingLotService.updateParkingLot(parkingLot));
    }

    /**
     * 删除车场管理
     */
    @PreAuthorize("@ss.hasPermi('system:lot:remove')")
    @Log(title = "车场管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lotIds}")
    public AjaxResult remove(@PathVariable Long[] lotIds)
    {
        return toAjax(parkingLotService.deleteParkingLotByLotIds(lotIds));
    }
}
