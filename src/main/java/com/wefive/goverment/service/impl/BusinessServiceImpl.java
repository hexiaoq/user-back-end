package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.BusinessDao;
import com.wefive.goverment.entity.BusinessEntity;
import com.wefive.goverment.service.BusinessService;


@Service("businessService")
public class BusinessServiceImpl extends ServiceImpl<BusinessDao, BusinessEntity> implements BusinessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BusinessEntity> page = this.page(
                new Query<BusinessEntity>().getPage(params),
                new QueryWrapper<BusinessEntity>()
        );

        return new PageUtils(page);
    }

}