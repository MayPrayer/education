package com.hbnu.statics.controller;


import com.hbnu.statics.service.StatisticsDailyService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@RestController
@RequestMapping("/statics/statistics-daily")
@CrossOrigin
@Slf4j
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("统计某一天的注册人数")
    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day) {
        statisticsDailyService.registerCount(day);
        return Result.sucess();
    }


    //图表显示 返回两部分数据：日期json数组、数量json数组
    @ApiOperation(value = "图表显示")
    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type, @PathVariable String begin,
                           @PathVariable String end) {
        Map<String, Object> map = statisticsDailyService.getShowData(type, begin, end);
        return Result.sucess().setData(map);
    }

}

