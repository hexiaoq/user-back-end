package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.MaterialDao;
import com.wefive.goverment.entity.MaterialEntity;
import com.wefive.goverment.service.MaterialService;


@Service("materialService")
public class MaterialServiceImpl extends ServiceImpl<MaterialDao, MaterialEntity> implements MaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaterialEntity> page = this.page(
                new Query<MaterialEntity>().getPage(params),
                new QueryWrapper<MaterialEntity>()
        );

        return new PageUtils(page);
    }

}