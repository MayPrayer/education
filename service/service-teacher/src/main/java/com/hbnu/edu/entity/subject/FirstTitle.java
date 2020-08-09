package com.hbnu.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FirstTitle <br/>
 * Description: <br/>
 * date: 2020/8/9 11:54<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
public class FirstTitle {
    private String id;
    private String title;
    private List<SecondTitle> children =new ArrayList<>();
}
