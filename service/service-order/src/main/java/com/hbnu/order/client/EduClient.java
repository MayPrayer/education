package com.hbnu.order.client;

import com.hbnu.util.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname EduClient
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/18 9:59
 */
@Component
@FeignClient("service-teacher")
public interface EduClient {

    @GetMapping("/edu/coursefront/getcourseinforder/{courseId}")
    public CourseWebVoOrder getCourseInforder(@PathVariable("courseId") String courseId);
}
