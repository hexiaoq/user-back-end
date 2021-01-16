package com.wefive.goverment.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wefive.goverment.entity.UserorderEntity;

@Mapper
public interface UserorderDao extends BaseMapper<UserorderEntity>{
	@Delete("delete from userorder where user_id=#{userId} and dept_id=#{deptId}")
	void cancelorder(int deptId,int userId);
}
