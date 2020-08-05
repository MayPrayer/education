package com.hbnu.edu.controller;

import com.hbnu.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * ClassName: LoginController <br/>
 * Description: <br/>
 * date: 2020/8/3 9:35<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
@CrossOrigin  //解决跨域问题
public class LoginController {


    @PostMapping("/login")
    public Result login(){
        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("token","admin");
        return Result.sucess().setData(hashMap);
    }


    @GetMapping("/info")
    public Result info(){
        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("role","[admin]");
        hashMap.put("name","zhangsan");
        hashMap.put("avatar","https://www.baidu.com/link?url=_CESbUkgNaFM0XXU3obm2kPGqjjNP4U78p5SSHKP_PRApmDT0Yk-FU3ofrL_WfgjQ5P_4HzmAb5lgIcgN2MSyVyzAbR2O051-rc0hWZyGSD005Q3wC4We9uiAZYQEX8yQU0dmc0ImG69vlU4mQdiebCp9Pkt4SQsT8O0mKRHKyOdJlX1ZZ1m9VwKF4pbESVcD_zjX2WeAhr_1FFr3g2AVX3Nd2xX-V9bJ9iy1yPOkYZYmPUMSuPiqGV_IAbm00R7u_ZVU2ls6hgSDrIQzcJsB2hETnw67vWfzWII3TgwbW0HKph0oih3dFosOM5sN9UfDFkLUoQMOXOFtwqrlG6HXiYw_2Jcen96leE8Xv4Xt4A5SbwJI4iO-jmnPB8BB1srGZn8Lb-w6O4UNXmOyNAueWIZWuQjNyrUAX48AgThNPPeArbTiIXfdlw4L0SYXNVjfS7Q0XTTl8sAIEZfXCdP1SFR7-o1gGw4D2-P_phxhgDas5V1nGgPIf7Y94CvsAUqIb8KGZbS0DK0wh5BjK0aCa1U4p1vgTdrtZDdtLivhh_fw2gBGOGGUrKxBd83b5YImdO2mOP8Y5hxQq0WqlLd_ZVRVAjHb6jJb1itTuC_IyW&timg=&click_t=1596420031158&s_info=1730_861&wd=&eqid=b694f8c90042c7aa000000035f276fbb");

        return Result.sucess().setData(hashMap);
    }

}
