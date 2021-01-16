package com.wefive.goverment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("userorder")
public class UserorderEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId
	private Integer orderId;
	private Integer userId;
	private Integer deptId;
	private Integer orderTime;
	private LocalDate orderDay;
	/**
	 * 
	 */

}
