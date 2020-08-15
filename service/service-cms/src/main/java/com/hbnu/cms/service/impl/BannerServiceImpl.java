package com.hbnu.cms.service.impl;

import com.hbnu.cms.entity.Banner;
import com.hbnu.cms.mapper.BannerMapper;
import com.hbnu.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-14
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public List<Banner> getAllBanner() {
        List<Banner> banners= this.list();
        return banners;
    }
}
