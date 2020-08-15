package com.hbnu.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.cms.entity.Banner;
import com.hbnu.cms.service.BannerService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/cms/fontbanner")
@Slf4j
@CrossOrigin
public class BannerFontController {
    @Autowired
    private BannerService bannerService;

//使用redis查询注解
    @Cacheable(key = "'selectIndexList'",value = "banner")
    @ApiOperation("查询所有banner")
    @GetMapping("getallbanner")
    public Result getAllBanner() {
        log.error("执行了查询banner代码");
        List<Banner> banners = bannerService.getAllBanner();
        return Result.sucess().setData(banners);
    }
}

