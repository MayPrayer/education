package com.hbnu.edu.controller;


import com.hbnu.base.config.exception.MyException;
import com.hbnu.edu.entity.Chapter;
import com.hbnu.edu.entity.vo.ChapterInfo;
import com.hbnu.edu.service.ChapterService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //这里是根据课程id获取多有章节
    @ApiOperation(value = "获取所有章节")
    @GetMapping("getchapter/{courseid}")
    public Result getChapter(@PathVariable("courseid") String courseid) {
        List<ChapterInfo> chapters = chapterService.getChapter(courseid);
        return Result.sucess().setData(chapters);
    }

    @ApiOperation(value = "添加一个章节")
    @PostMapping("addchapter")
    public Result addChapter(@RequestBody Chapter chapter) {
        //打印下接收的对象
        log.error("对象为：[{}]", chapter);
        Boolean result = chapterService.save(chapter);
        return result ? Result.sucess() : Result.failed();
    }

    @ApiOperation(value = "修改章节")
    @PostMapping("updatechapter")
    public Result updateChapter(@RequestBody Chapter chapter) {
        Boolean result = chapterService.updateById(chapter);
        log.error("修改对象为：[{}]", chapter);
        return result ? Result.sucess() : Result.failed();
    }


    @ApiOperation(value = "根据章节id查询当前章节")
    @GetMapping("getOnechapter/{chapterid}")
    public Result findChapter(@PathVariable String chapterid) {
        Chapter chapter = chapterService.getById(chapterid);
        return Result.sucess().setData(chapter);
    }


    @ApiOperation(value = "删除章节")
    @DeleteMapping("{chapterid}")
    public Result deleteChapter(@PathVariable String chapterid) {
        Boolean result = false;
        try {
            result = chapterService.deleteChapter(chapterid);
        } catch (MyException e
        ) {
            log.error("章节下还有小结，无法删除");
            return Result.failed();
        }
        return result ? Result.sucess() : Result.failed();
    }

}

