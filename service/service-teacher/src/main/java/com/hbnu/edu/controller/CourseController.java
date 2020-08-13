package com.hbnu.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.vo.CourseInfo;
import com.hbnu.edu.entity.vo.CourseQuery;
import com.hbnu.edu.entity.vo.FinalCourseInfo;
import com.hbnu.edu.service.CourseService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/edu/course")
@CrossOrigin
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("addcourse")
    public Result addCourse(@RequestBody CourseInfo courseInfo) {
        String course_id = courseService.addOneCourse(courseInfo);
        return Result.sucess().setData(course_id);
    }

    @ApiOperation(value = "根据课程id查询课程信息")
    @GetMapping("findcourse/{id}")
    public Result findCourse(@PathVariable("id") String courseid) {
        CourseInfo courseInfo = courseService.findCourseByCId(courseid);
        log.info("执行了查询课程信息，并且返回了");
        return Result.sucess().setData(courseInfo);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("updatecourse")
    public Result updateCourse(@RequestBody CourseInfo courseInfo) {
        Boolean result = courseService.UpdateCourse(courseInfo);
        return result ? Result.sucess() : Result.failed();
    }

    @ApiOperation(value = "获取发布课程信息")
    @GetMapping("finalcourse/{courseid}")
    public Result getFinalCourseInfo(@PathVariable String courseid) {
        FinalCourseInfo finalCourseInfo = courseService.getAllFinalCourseInfo(courseid);
        return Result.sucess().setData(finalCourseInfo);
    }

    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return Result.sucess();
    }


    //课程列表分页查询显示带条件
    //current 当前页
    //limit 每页记录数
    @ApiOperation(value = "课程列表分页查询显示带条件")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                      @RequestBody(required = false) CourseQuery courseQuery) {
        log.error("执行了分页曹祖o");
        //创建page对象
        Page<Course> pageCourse = new Page<>(current, limit);
        //构建条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis学过 动态sql
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pageCourse, wrapper);
        long total = pageCourse.getTotal();//总记录数
        List<Course> records = pageCourse.getRecords(); //数据list集合
        return Result.sucess().setData(records).setCount(total);

    }

    //删除课程
//    @ApiOperation(value = "删除课程")
//    @DeleteMapping("deleteCourseById/{courseId}")
//    public Result deleteCourse(@PathVariable String courseId) {
//        courseService.removeCourse(courseId);
//        return Result.sucess();
//    }

    @ApiOperation("根据课程id更改销售量")
    @GetMapping("updateBuyCount/{id}")
    public Result updateBuyCountById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id) {
        courseService.updateBuyCountById(id);
        return Result.sucess();
    }

}

