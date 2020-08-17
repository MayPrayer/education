package com.hbnu.msm.service;

import java.util.Map;

/**
 * @Classname MsmService
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/15 14:26
 */
public interface MsmService {
    Boolean sendMsg(Map<String, Object> param, String phone);
    void bombMsg(Map<String, Object> param, String phone ,int count);
}
