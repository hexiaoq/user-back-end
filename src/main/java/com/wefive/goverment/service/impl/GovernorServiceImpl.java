package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.GovernorDao;
import com.wefive.goverment.entity.GovernorEntity;
import com.wefive.goverment.service.GovernorService;


@Service("governorService")
public class GovernorServiceImpl extends ServiceImpl<GovernorDao, GovernorEntity> implements GovernorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GovernorEntity> page = this.page(
                new Query<GovernorEntity>().getPage(params),
                new QueryWrapper<GovernorEntity>()
        );

        return new PageUtils(page);
    }

}