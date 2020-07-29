package com.hbnu.edu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbnu.edu.entity.Teacher;
import com.hbnu.edu.entity.vo.TeacherQuery;
import com.hbnu.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.Get;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.hbnu.util.Result;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "返回所有教师信息集合，不包括已经删除的教师信息")
    @GetMapping("/findAll")
    public List<Teacher> getAllTeacher() {
        return teacherService.list();
    }

    /*删除指定id教师记录*/
    @ApiOperation(value = "返回删除信息结果")
    @DeleteMapping("{id}")
    public String delOneTeacher(@PathVariable("id") long id) {
        //模拟异常
        int i =1/0;
        Boolean reuslt = teacherService.removeById(id);
        if (reuslt) {
            return "删除成功";
        } else {
            return "已经删除，无法再次删除";
        }
    }

    @ApiOperation(value = "返回教师分页数据，但是不包括已删除的教师信息")
    @GetMapping("pageTeacher/{curpage}/{pagesize}")
    public Result getTeacherPage(@PathVariable("curpage") int curpage, @PathVariable("pagesize") int pagesize) {
        //当前页面 页面显示条数
        Page<Teacher> page = new Page(curpage, pagesize);
//        调用方法开始分页，将结果封装到page中，前提是在配置中开启分页插件
        teacherService.page(page);
        //返回数据集合
        List<Teacher> datalist = page.getRecords();
        return Result.sucess().setCount(page.getTotal()).setData(datalist);

    }


    @ApiOperation(value = "多条件查询")
    @PostMapping("pageTeacherConditions/{curpage}/{pagesize}")
    /* @RequestBody  用于post提交，且required = false表示不是必传选项 */
    public Result getTeacherPageByCond(@PathVariable("curpage") int curpage, @PathVariable("pagesize") int pagesize, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> page = new Page(curpage, pagesize);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper();
        //获取条件
        String name = teacherQuery.getName();
        Date GmtCreate = teacherQuery.getGmtCreate();
        Date GmtModified = teacherQuery.getGmtModified();
        Integer level = teacherQuery.getLevel();
        //判断条件不为空,拼装查询条件
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(GmtCreate)){
            queryWrapper.ge("gmt_create",GmtCreate);
        }
        if (!StringUtils.isEmpty(GmtModified)){
            queryWrapper.le("gmt_codified",GmtModified);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        teacherService.page(page,queryWrapper);
        //返回数据集合
        List<Teacher> datalist = page.getRecords();
        return Result.sucess().setCount(page.getTotal()).setData(datalist);
    }

    @ApiOperation(value = "教师添加")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher){
           Boolean result =  teacherService.save(teacher);
           if (result){
               return Result.sucess();
           }
           else{
            return Result.failed();
        }
    }


    @ApiOperation(value = "教师信息修改")
    @PostMapping("modifTeacher")
    public Result modifTeacher(@RequestBody Teacher teacher){
        Boolean result =  teacherService.updateById(teacher);
        if (result){
            return Result.sucess();
        }
        else{
            return Result.failed();
        }
    }


}

