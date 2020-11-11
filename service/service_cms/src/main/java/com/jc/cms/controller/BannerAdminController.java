package com.jc.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cms.entity.CrmBanner;
import com.jc.cms.service.CrmBannerService;
import com.jc.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner, null);
        return Result.ok().data("items", pageBanner.getRecords())
                .data("total", pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return Result.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return Result.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Result updateById(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return Result.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Result deleteById(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Result.ok();
    }
}

