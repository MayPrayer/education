package com.hbnu.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.cms.entity.Banner;
import com.hbnu.cms.service.BannerService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/adminbanner")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation("分页查询banner")
    @GetMapping("pagebanner/{page}/{pagesize}")
    public Result getPageBanner(@PathVariable("page")long page,@PathVariable("pagesize")long pagesize){
        Page<Banner> bannerPage = new Page(page,pagesize);
        bannerService.page(bannerPage);
        return Result.sucess().setCount(bannerPage.getTotal()).setData(bannerPage.getRecords());
    }

    @ApiOperation("添加banner")
    @PostMapping("addbanner")
    public Result addBanner(@RequestBody Banner banner){
        bannerService.save(banner);
        return Result.sucess();
    }

    @ApiOperation("删除banner")
    @DeleteMapping("{id}")
    public Result delBanner(@PathVariable String id){
        bannerService.removeById(id);
        return Result.sucess();
    }

    @ApiOperation("修改banner")
    @PostMapping("updatebanner")
    public Result UpdateBanner(@RequestBody Banner banner){
        bannerService.updateById(banner);
        return Result.sucess();
    }

}
