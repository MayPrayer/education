package com.hbnu.edu.service;

import com.hbnu.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
public interface VideoService extends IService<Video> {

    List<Video> getVideo(String courseid);
}
