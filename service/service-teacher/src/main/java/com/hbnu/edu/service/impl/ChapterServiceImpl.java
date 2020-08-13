package com.hbnu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.base.config.exception.MyException;
import com.hbnu.edu.entity.Chapter;
import com.hbnu.edu.entity.Video;
import com.hbnu.edu.entity.vo.ChapterInfo;
import com.hbnu.edu.entity.vo.VideoInfo;
import com.hbnu.edu.mapper.ChapterMapper;
import com.hbnu.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private VideoService videoService;

    @Override
    public Boolean deleteChapter(String chapterid)throws MyException {
        //查询 小结中是否还有
        QueryWrapper<Video> queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id", chapterid);
        //查询章节中小结数量
        int count = videoService.count(queryWrapper);
        if (0==count ){
           this.removeById(chapterid);
           return true;
        }else{
          throw new MyException("章节下方还有小结无法删除！",30000);
        }
    }

    @Override
    public List<ChapterInfo> getChapter(String courseid) {
        //查询所有章节信息
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", courseid);
        List<Chapter> chapters = this.list(queryWrapper);

        //查询所有小结，视频信息
        List<Video> videos = videoService.getVideo(courseid);

        //创建返回最终数据
        List<ChapterInfo> finalChapterInfo = new ArrayList<>();

        //遍历封装数据
        for (Chapter chapter : chapters) {
            //遍历一次，创建一个章节
            ChapterInfo chapterInfo = new ChapterInfo();
            //赋值
            BeanUtils.copyProperties(chapter, chapterInfo);
            for (Video video : videos) {
                if (video.getChapterId().equals(chapter.getId())) {
                    //如果该小结属于该章节，则创建小结对象
                    VideoInfo videoinfo = new VideoInfo();
                    //赋值
                    BeanUtils.copyProperties(video, videoinfo);
                    //将小结加入章节列表下
                    chapterInfo.getVideos().add(videoinfo);
                }
            }
            //遍历完一个章节下的每个小结后，开始将这个章节封装到集合
            finalChapterInfo.add(chapterInfo);
        }
        return finalChapterInfo;
    }

    //根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

}
