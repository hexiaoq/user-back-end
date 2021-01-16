package com.wefive.goverment.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.dao.OrderNumberDao;
import com.wefive.goverment.entity.OrderNumberEntity;
import com.wefive.goverment.entity.UserorderEntity;
import com.wefive.goverment.service.OrderNumberService;


@Service("orderNumberService")
public class OrderNumberServiceImpl extends ServiceImpl<OrderNumberDao, OrderNumberEntity> implements OrderNumberService{
	@Resource
	private OrderNumberDao orderNumberDao;
	@Resource
	private OrderNumberService orderNumberService;
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addn(UserorderEntity userorderEntity) {
		int deptId=userorderEntity.getDeptId();
		LocalDate orderDay=userorderEntity.getOrderDay();
		int orderTime=userorderEntity.getOrderTime();
		OrderNumberEntity orderNumberEntity=new OrderNumberEntity();
		orderNumberEntity.setDeptId(deptId);
		orderNumberEntity.setOrderDay(orderDay);
		orderNumberEntity.setOrderTime(orderTime);
		if((orderNumberService.getOne(new QueryWrapper<OrderNumberEntity>().eq("dept_id", deptId).eq("order_day", orderDay).eq("order_time", orderTime))) == null) {
			//System.out.println(orderNumberEntity);
			orderNumberService.save(orderNumberEntity);
		}
		else {
			orderNumberDao.addnum(orderNumberEntity.getDeptId(), orderNumberEntity.getOrderDay(), orderNumberEntity.getOrderTime());
		}
		return true;
	}
	public boolean cancel(UserorderEntity userorderEntity) {
		int deptId=userorderEntity.getDeptId();
		LocalDate orderDay=userorderEntity.getOrderDay();
		int orderTime=userorderEntity.getOrderTime();
		orderNumberDao.denum(deptId, orderDay, orderTime);
		if(orderNumberService.getOne(new QueryWrapper<OrderNumberEntity>().eq("dept_id", deptId).eq("order_day", orderDay).eq("order_time", orderTime)).getOrderNum() == 0) {
			orderNumberDao.delete(new QueryWrapper<OrderNumberEntity>().eq("dept_id", deptId).eq("order_day", orderDay).eq("order_time", orderTime));
		}
		return true;
	}
}
