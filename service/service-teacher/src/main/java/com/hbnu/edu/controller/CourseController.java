package com.hbnu.edu.controller;


import com.hbnu.edu.entity.vo.CourseInfo;
import com.hbnu.edu.service.CourseService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
       return result?Result.sucess():Result.failed();
    }
}

