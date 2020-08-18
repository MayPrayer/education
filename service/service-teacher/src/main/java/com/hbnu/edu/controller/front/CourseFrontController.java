package com.hbnu.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.fontvo.CourseFrontVo;
import com.hbnu.edu.entity.fontvo.CourseWebVo;
import com.hbnu.edu.entity.vo.ChapterInfo;
import com.hbnu.edu.service.ChapterService;
import com.hbnu.edu.service.CourseService;
import com.hbnu.util.Result;
import com.hbnu.util.ordervo.CourseWebVoOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "课程页功能")
@RestController
@RequestMapping("/edu/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;


    //1 条件查询带分页查询课程
    @ApiOperation(value = "条件查询带分页查询课程")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<Course> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        //返回分页所有数据
        return Result.sucess().setData(map);
    }


    //2 课程详情的方法
    @ApiOperation(value = " 课程详情的方法")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterInfo> chapterVideoList = chapterService.getChapter(courseId);
        Map<String, Object> map = new HashMap();
        map.put("courseWebVo", courseWebVo);
        map.put("chapterVideoList", chapterVideoList);
        return Result.sucess().setData(map);
    }

    @ApiOperation(value = " 根据课程id查询课程信息")
    @GetMapping("getcourseinforder/{courseId}")
    public CourseWebVoOrder getCourseInforder(@PathVariable String courseId) {
        Course course = courseService.getById(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(course,courseWebVoOrder);
        return courseWebVoOrder;
    }
}












