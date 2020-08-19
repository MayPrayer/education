package com.hbnu.statics.scheduling;

import com.hbnu.statics.service.StatisticsDailyService;
import com.hbnu.statics.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname ScheduTask
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/19 8:41
 */
@Component
public class ScheduTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //在线cron表达式只能取前六位
    @Scheduled(cron = "0 0 1 * * ? ")
    public void timeTodo(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
