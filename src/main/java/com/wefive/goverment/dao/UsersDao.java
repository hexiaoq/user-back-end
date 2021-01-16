package com.wefive.goverment.dao;

import com.wefive.goverment.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author wefive
 * @email 156437734@qq.com
 * @date 2020-12-07 10:48:44
 */
@Mapper
public interface UsersDao extends BaseMapper<UsersEntity> {
    void saveall(@Param("userId")int userId,@Param("name") String name, @Param("password")String password,
                 @Param("cardId")String cardId, @Param("phone")String phone);

	
}
