package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.DeptBusinessDao;
import com.wefive.goverment.entity.DeptBusinessEntity;
import com.wefive.goverment.service.DeptBusinessService;


@Service("deptBusinessService")
public class DeptBusinessServiceImpl extends ServiceImpl<DeptBusinessDao, DeptBusinessEntity> implements DeptBusinessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeptBusinessEntity> page = this.page(
                new Query<DeptBusinessEntity>().getPage(params),
                new QueryWrapper<DeptBusinessEntity>()
        );

        return new PageUtils(page);
    }

}