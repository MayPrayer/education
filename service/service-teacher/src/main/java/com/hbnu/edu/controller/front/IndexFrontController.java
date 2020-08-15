package com.hbnu.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.Teacher;
import com.hbnu.edu.service.CourseService;
import com.hbnu.edu.service.TeacherService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/edu/front")
@CrossOrigin
@Slf4j
public class IndexFrontController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    //查询出特定的两个数据
    @ApiOperation("查询出热点数据")
    @GetMapping("index")
    public Result getTwoCourse() {
        //获取降序排列的前4条课程数据
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper();
        queryWrapper1.orderByDesc("id").last("limit 4");
        List<Course> courseList = courseService.list(queryWrapper1);

        //获取讲师的前8条数据
        QueryWrapper<Teacher> queryWrapper2 = new QueryWrapper();
        queryWrapper2.orderByDesc("id").last("limit 8");
        List<Teacher> teacherList = teacherService.list(queryWrapper2);
        HashMap<String, Object> data = new HashMap();
        data.put("course", courseList);
        data.put("teacher", teacherList);
        return Result.sucess().setData(data);
    }
}
