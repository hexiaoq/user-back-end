package com.wefive.goverment.dao;

import java.time.LocalDate;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wefive.goverment.entity.OrderNumberEntity;

@Mapper
public interface OrderNumberDao extends BaseMapper<OrderNumberEntity>{
	@Update("update order_number set order_num=order_num+1 where dept_id=#{deptId} and order_day=#{orderDay} and order_time=#{orderTime}")
	void addnum(int deptId,LocalDate orderDay,int orderTime);
	@Update("update order_number set order_num=order_num-1 where dept_id=#{deptId} and order_day=#{orderDay} and order_time=#{orderTime}")
	void denum(int deptId,LocalDate orderDay,int orderTime);
}
