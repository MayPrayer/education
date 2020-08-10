package com.hbnu.edu.service;

import com.hbnu.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbnu.edu.entity.vo.ChapterInfo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
public interface ChapterService extends IService<Chapter> {

     List<ChapterInfo> getChapter(String courseid);
}
