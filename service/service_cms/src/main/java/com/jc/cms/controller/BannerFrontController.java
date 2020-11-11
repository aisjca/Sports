package com.jc.cms.controller;


import com.jc.cms.entity.CrmBanner;
import com.jc.cms.service.CrmBannerService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return Result.ok().data("list", list);
    }
}

