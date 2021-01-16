package com.wefive.goverment.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.entity.UserorderEntity;

public interface UserorderService extends IService<UserorderEntity>{
	PageUtils queryPage(Map<String, Object> params);
	boolean ordercancel(int userId,int deptId);
}
