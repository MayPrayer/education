package com.hbnu.edu.controller;


import com.hbnu.edu.entity.subject.FirstTitle;
import com.hbnu.edu.service.SubjectService;
import com.hbnu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;


    @PostMapping("/addsubject")
    public Result addSubject(@RequestParam MultipartFile file){
        subjectService.addSubject(file,subjectService);
        return Result.sucess();
    }


    @GetMapping("/list")
    public Result listSubject(){
       List<FirstTitle> list = subjectService.getAllSubject();
        return Result.sucess().setData(list);
    }
}

