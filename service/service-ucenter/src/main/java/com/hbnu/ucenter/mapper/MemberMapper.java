package com.hbnu.ucenter.mapper;

import com.hbnu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-15
 */
public interface MemberMapper extends BaseMapper<Member> {
     Integer countRegisterDay(String day);
}
