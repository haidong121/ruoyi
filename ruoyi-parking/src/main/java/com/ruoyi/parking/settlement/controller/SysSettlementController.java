package com.ruoyi.parking.settlement.controller;

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
import com.ruoyi.parking.settlement.domain.SysSettlement;
import com.ruoyi.parking.settlement.service.ISysSettlementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 结算管理Controller
 * 
 * @author zhuez
 * @date 2022-04-02
 */
@RestController
@RequestMapping("/settlement/settlement")
public class SysSettlementController extends BaseController
{
    @Autowired
    private ISysSettlementService sysSettlementService;

    /**
     * 查询结算管理列表
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSettlement sysSettlement)
    {
        startPage();
        List<SysSettlement> list = sysSettlementService.selectSysSettlementList(sysSettlement);
        return getDataTable(list);
    }

    /**
     * 导出结算管理列表
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:export')")
    @Log(title = "结算管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSettlement sysSettlement)
    {
        List<SysSettlement> list = sysSettlementService.selectSysSettlementList(sysSettlement);
        ExcelUtil<SysSettlement> util = new ExcelUtil<SysSettlement>(SysSettlement.class);
        util.exportExcel(response, list, "结算管理数据");
    }

    /**
     * 获取结算管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:query')")
    @GetMapping(value = "/{settlementId}")
    public AjaxResult getInfo(@PathVariable("settlementId") Long settlementId)
    {
        return AjaxResult.success(sysSettlementService.selectSysSettlementBySettlementId(settlementId));
    }

    /**
     * 新增结算管理
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:add')")
    @Log(title = "结算管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSettlement sysSettlement)
    {
        return toAjax(sysSettlementService.insertSysSettlement(sysSettlement));
    }

    /**
     * 修改结算管理
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:edit')")
    @Log(title = "结算管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSettlement sysSettlement)
    {
        return toAjax(sysSettlementService.updateSysSettlement(sysSettlement));
    }

    /**
     * 删除结算管理
     */
    @PreAuthorize("@ss.hasPermi('settlement:settlement:remove')")
    @Log(title = "结算管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{settlementIds}")
    public AjaxResult remove(@PathVariable Long[] settlementIds)
    {
        return toAjax(sysSettlementService.deleteSysSettlementBySettlementIds(settlementIds));
    }
}
