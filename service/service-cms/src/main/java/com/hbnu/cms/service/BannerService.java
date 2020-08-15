package com.hbnu.cms.service;

import com.hbnu.cms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-14
 */
public interface BannerService extends IService<Banner> {

    List<Banner> getAllBanner();
}
