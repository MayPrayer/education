package com.hbnu.ucenter.service;

import com.hbnu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbnu.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-15
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    Boolean register(RegisterVo register);

    Member parseToken(String id);
}
