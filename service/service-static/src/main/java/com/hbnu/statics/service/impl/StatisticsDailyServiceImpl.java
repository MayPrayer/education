package com.hbnu.statics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.statics.controller.client.MemClient;
import com.hbnu.statics.entity.StatisticsDaily;
import com.hbnu.statics.mapper.StatisticsDailyMapper;
import com.hbnu.statics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.util.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private MemClient memClient;
    @Override
    public void registerCount(String day) {
        //相同日期时  先删除旧数据  再添加
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        //获取当天注册人数
       Result result =  memClient.countRegister(day);
       int registercount  = (Integer) result.getData();

       //同时将人数数据插入至daily表中
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registercount);
        daily.setDateCalculated(day);
        daily.setVideoViewNum(RandomUtils.nextInt(100,200));
        daily.setLoginNum(RandomUtils.nextInt(100,200));
        daily.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        //返回的数据：1.日期 2.日期对应的结构
        //前端json数组对应后端的list集合
        //创建日期list 数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        //遍历查询所有数据的list集合statisticsDailies 进行封装
        for (int i = 0; i < statisticsDailies.size(); i++) {
            StatisticsDaily daily = statisticsDailies.get(i);
            //封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            //封装对应的具体数量（登录 注册 播放等）
            switch (type){
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
