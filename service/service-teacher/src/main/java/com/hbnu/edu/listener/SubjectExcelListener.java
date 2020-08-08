package com.hbnu.edu.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.base.config.exception.MyException;
import com.hbnu.edu.entity.Subject;
import com.hbnu.edu.excel.SubjectData;
import com.hbnu.edu.service.SubjectService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: ExcelListener <br/>
 * Description: <br/>
 * date: 2020/8/8 9:30<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Slf4j
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /*
     * 该类无法交由spring管理，所以只能通过手动注入
     *
     * */
    private SubjectService subjectService;

    public SubjectExcelListener() {

    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @SneakyThrows
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new MyException("文件数据为空", 300000);
        } else {
            if (!existFirstClassfiy(subjectService, subjectData.getFirstClassify())) {
                //表中不存在该一级分类，自己添加
                Subject subject = new Subject();
                subject.setTitle(subjectData.getFirstClassify());
                log.info("excel内容为{}",subjectData.getFirstClassify());
                log.info("subject1内容为[{}]",subject);
                subjectService.save(subject);
            }
            //开始读取二级分类,首先获取从数据库获取父id ，根据父类名字获取
           String pid = selectByName(subjectService,subjectData.getFirstClassify()).getId();

            if (!existSecondClassfiy(subjectService, subjectData.getSecondClassify(),pid)){
                Subject subject = new Subject();
                subject.setParentId(pid);
                subject.setTitle(subjectData.getSecondClassify());
                log.info("excel内容为{}",subjectData.getSecondClassify());
               log.info("subject2内容为[{}]",subject);
                subjectService.save(subject);
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /*
     * 判断一级分类是否存在 ,父id为0
     * */
    public Boolean existFirstClassfiy(SubjectService subjectService, String name) {
        //查询名字对应的一级分类是否存在
        QueryWrapper<Subject> queryWrapper = new QueryWrapper();
        //select  * from edu_subject where title=? and id = 0
        queryWrapper.eq("title", name).eq("parent_id", 0);
        Subject subject = subjectService.getOne(queryWrapper);
        return subject == null ? false : true;
    }

    /*
     *判断二级分类是否存在，id不能存在
     *
     * */
    public Boolean existSecondClassfiy(SubjectService subjectService, String name, String pid) {
        //查询名字对应的一级分类是否存在
        QueryWrapper<Subject> queryWrapper = new QueryWrapper();
        //select  * from edu_subject where title=? and id = 0
        queryWrapper.eq("title", name).eq("parent_id", pid);
        Subject subject = subjectService.getOne(queryWrapper);
        return subject == null ? false : true;
    }

    /*
    * 根据一级分类名查询id
    * */
    public Subject selectByName(SubjectService subjectService,String name){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        return subjectService.getOne(queryWrapper);
    }
}
