package com.hbnu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.edu.entity.Video;
import com.hbnu.edu.mapper.VideoMapper;
import com.hbnu.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public List<Video> getVideo(String courseid) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", courseid);
        List<Video> videos = this.list(queryWrapper);
        return videos;
    }
}
