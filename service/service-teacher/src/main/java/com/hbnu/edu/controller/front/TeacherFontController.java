package com.hbnu.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.Teacher;
import com.hbnu.edu.service.CourseService;
import com.hbnu.edu.service.TeacherService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname TeacherFontController
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/17 13:31
 */
@RestController
@RequestMapping("/edu/front")
@CrossOrigin
@Slf4j
public class TeacherFontController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @ApiOperation("名师列表显示")
    @PostMapping("getAllTeacher/{curpage}/{pagesize}")
    public Result getFontAllTeacher(@PathVariable long curpage, @PathVariable long pagesize) {

        Page<Teacher> pageTeacher = new Page(curpage, pagesize);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //把分页数据封装到pageTeacher对象里去
        teacherService.pageMaps(pageTeacher, wrapper);

        List<Teacher> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();//下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return Result.sucess().setData(map);
    }


    //2 讲师详情的功能
    @ApiOperation(value = "讲师详情的功能")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId) {
        //1 根据讲师id查询讲师基本信息
        Teacher eduTeacher = teacherService.getById(teacherId);
        //2 根据讲师id查询所讲课程
        QueryWrapper<Course> wrapper = new QueryWrapper();
        wrapper.eq("teacher_id", teacherId);
        List<Course> courseList = courseService.list(wrapper);
        HashMap<String, Object> map = new HashMap();
        map.put("teacher", eduTeacher);
        map.put("courseList", courseList);
        return Result.sucess().setData(map);
    }
}
