package com.wefive.goverment.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.entity.OrderNumberEntity;
import com.wefive.goverment.entity.UserorderEntity;

public interface OrderNumberService extends IService<OrderNumberEntity>{
	PageUtils queryPage(Map<String, Object> params);
	boolean addn(UserorderEntity userOrderEntity);
	boolean cancel(UserorderEntity userorderEntity);
}
