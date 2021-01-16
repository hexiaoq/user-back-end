package com.wefive.goverment.service.impl;

import com.wefive.goverment.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;

import com.wefive.goverment.dao.UsersDao;
import com.wefive.goverment.entity.UsersEntity;
import com.wefive.goverment.service.UsersService;


@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
                new Query<UsersEntity>().getPage(params),
                new QueryWrapper<UsersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveall(UsersEntity user) {
        String password = user.getPassword();
        String cardId = user.getCardId();
        String phone = user.getPhone();
        Integer userId = user.getUserId();
        String name = user.getName();
        this.baseMapper.saveall(userId,name,password,cardId,phone);
    }

}