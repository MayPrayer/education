package com.hbnu.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * ClassName: SubjectData <br/>
 * Description: <br/>
 * date: 2020/8/8 9:11<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class SubjectData {
    @ExcelProperty(value = "一级目录",index = 0)   //一级分类 ，在excel第一列
    private String firstClassify;
    @ExcelProperty(value = "二级目录",index = 1)  //二级分类，在excel第二列
    private String secondClassify;
}
