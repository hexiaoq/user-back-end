package com.wefive.goverment.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.Query;
import com.wefive.goverment.dao.OrderNumberDao;
import com.wefive.goverment.dao.UserorderDao;
import com.wefive.goverment.entity.UserorderEntity;
import com.wefive.goverment.entity.UsersBusinessEntity;
import com.wefive.goverment.service.OrderNumberService;
import com.wefive.goverment.service.UserorderService;

@Service("userorderService")
public class UserorderServiceImpl extends ServiceImpl<UserorderDao, UserorderEntity> implements UserorderService{
	@Resource
	private UserorderDao userorderDao;
	@Resource
	private UserorderService userorderService;
	@Resource
	private OrderNumberDao orderNumberDao;
	@Resource
	private OrderNumberService orderNumberService;
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		IPage<UserorderEntity> page = this.page(
                new Query<UserorderEntity>().getPage(params),
                new QueryWrapper<UserorderEntity>()
        );
		return new PageUtils(page);
	}

	@Override
	public boolean ordercancel(int userId, int deptId) {
		// TODO Auto-generated method stub
		UserorderEntity userorderEntity=userorderService.getOne(new QueryWrapper<UserorderEntity>().eq("user_id", userId).eq("dept_id", deptId));
		//UserorderEntity userorderEntity=userorderService.getOne(new QueryWrapper<UserorderEntity>().exists(existsSql));
		if(userorderEntity == null) {
			return false;
		}
		else {
			userorderDao.cancelorder(deptId, userId);
			orderNumberService.cancel(userorderEntity);
			//orderNumberDao.denum(userorderEntity.getDeptId(), userorderEntity.getOrderDay(), userorderEntity.getOrderTime());
		}
		return true;
	}
}
