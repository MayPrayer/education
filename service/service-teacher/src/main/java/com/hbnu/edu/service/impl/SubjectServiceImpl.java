package com.hbnu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.edu.entity.Subject;
import com.hbnu.edu.entity.subject.FirstTitle;
import com.hbnu.edu.entity.subject.SecondTitle;
import com.hbnu.edu.excel.SubjectData;
import com.hbnu.edu.listener.SubjectExcelListener;
import com.hbnu.edu.mapper.SubjectMapper;
import com.hbnu.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-08
 */
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void addSubject(MultipartFile file, SubjectService subjectService) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("获取文件输入流失败！");
        }

        EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
    }

    @Override
    public List<FirstTitle> getAllSubject() {
        //自定义返回类型
        List<FirstTitle> finalResult = new ArrayList<>();

        //查询处所有一级分类
        QueryWrapper<Subject> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", 0);
        List<Subject> subjects = this.list(queryWrapper);

        log.error("返回一级目录信息[{}]",subjects);

        //查询二级分类
        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper();
        queryWrapper.ne("parent_id", 0);
        List<Subject> subjects2 = this.list(queryWrapper2);

        log.error("返回二级级目录信息[{}]",subjects2);

        //封装 遍历赋值
        for (int i = 0; i < subjects.size(); i++) {
            //创建一级目录对象
            FirstTitle result = new FirstTitle();
            //赋值一级目录
            BeanUtils.copyProperties(subjects.get(i), result);
            //遍历封装二级分类
            for (int m = 0; m < subjects2.size(); m++) {
                //如果找到相同的父级
                if (subjects2.get(m).getParentId().equals(subjects.get(i).getId())) {
                    //创建二级目录对象，赋值
                    SecondTitle secondTitle = new SecondTitle();
                    BeanUtils.copyProperties(subjects2.get(m), secondTitle);
                    //将兑现添加到一级目录
                    List list = result.getChildren();
                    list.add(secondTitle);
                }

            }
            //遍历完一次添加一次
            finalResult.add(result);
        }
        //遍历完所有返回
        log.info("返回分类结果为：[{}]",finalResult);
            return finalResult;
        }

    }
