package com.hbnu.edu.controller;


import com.hbnu.edu.entity.Teacher;
import com.hbnu.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-07-29
 */
@RestController //返回json数据
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /*查询所有教师*/
    @GetMapping("/findAll")
    public List<Teacher> getAllTeacher(){
        return teacherService.list();
    }

}

