package com.hbnu.edu.controller;


import com.hbnu.edu.entity.Chapter;
import com.hbnu.edu.entity.vo.ChapterInfo;
import com.hbnu.edu.service.ChapterService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value="获取所有章节")
    @GetMapping("getchapter/{id}")
    public Result getChapter(@PathVariable("id") String courseid){
        List<ChapterInfo> chapters = chapterService.getChapter(courseid);
        return Result.sucess().setData(chapters);
    }
}

