package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.UsersBusinessDao;
import com.wefive.goverment.entity.UsersBusinessEntity;
import com.wefive.goverment.service.UsersBusinessService;


@Service("usersBusinessService")
public class UsersBusinessServiceImpl extends ServiceImpl<UsersBusinessDao, UsersBusinessEntity> implements UsersBusinessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersBusinessEntity> page = this.page(
                new Query<UsersBusinessEntity>().getPage(params),
                new QueryWrapper<UsersBusinessEntity>()
        );

        return new PageUtils(page);
    }

}