package com.wefive.goverment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.entity.UsersBusinessEntity;

import java.util.Map;

/**
 * 
 *
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
public interface UsersBusinessService extends IService<UsersBusinessEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

