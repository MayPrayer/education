package com.hbnu.edu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbnu.base.config.exception.MyException;
import com.hbnu.edu.entity.Course;
import com.hbnu.edu.entity.CourseDescription;
import com.hbnu.edu.entity.vo.CourseInfo;
import com.hbnu.edu.entity.vo.FinalCourseInfo;
import com.hbnu.edu.mapper.CourseMapper;
import com.hbnu.edu.service.ChapterService;
import com.hbnu.edu.service.CourseDescriptionService;
import com.hbnu.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.edu.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-10
 */
@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ChapterService chapterService;

    @Override
    public String addOneCourse(CourseInfo courseInfo) {
        //创建课程对象，并赋值 ,保存入课程表
        Course course = new Course();  //创建对象的时候就已经自动生成id了
        BeanUtils.copyProperties(courseInfo, course);
        this.save(course);
        //创建课程简介对象并赋值
        String course_id = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course_id);
        courseDescription.setDescription(courseInfo.getDescription());
        log.info("id为{}", courseDescription.getId());
        //保存入两张表
        courseDescriptionService.saveOneCourseDesc(courseDescription);
        return course_id;
    }

    @Override
    public CourseInfo findCourseByCId(String courseid) {
        Course course = this.getById(courseid);
        CourseDescription courseDescription = courseDescriptionService.selectDescByCId(courseid);
        //创建返回VO类对象
        CourseInfo courseInfo = new CourseInfo();
        //封装数据
        BeanUtils.copyProperties(course, courseInfo);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public Boolean UpdateCourse(CourseInfo courseInfo) {
        //将vo对象转换成po对象
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);
        //保存至数据库
        Boolean result1 = this.saveOrUpdate(course);
        Boolean result2 = courseDescriptionService.UpdateCourse(courseDescription);
        return result1 && result2 ? true : false;
    }

    @Override
    public FinalCourseInfo getAllFinalCourseInfo(String courseid) {
        return baseMapper.getFinalCousrseInfo(courseid);
    }

    //删除课程
//    @Override
//    public void removeCourse(String courseId) throws MyException {
//        //1 根据课程id删除小节
//        videoService.removeVideoByCourseId(courseId);
//
//        //2 根据课程id删除章节
//        chapterService.removeChapterByCourseId(courseId);
//
//        //3 根据课程id删除描述
//        courseDescriptionService.removeById(courseId);
//
//        //4 根据课程id删除课程本身
//        int result = baseMapper.deleteById(courseId);
//        if(result == 0) { //失败返回
//            throw new MyException("删除失败",20001);
//        }
//    }

    //支付后，更新课程销量
    @Override
    public void updateBuyCountById(String id) {
        Course course = baseMapper.selectById(id);
        course.setBuyCount(course.getBuyCount() + 1);
        this.updateById(course);
    }
}
