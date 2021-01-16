package com.wefive.goverment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@Component
@TableName("order_number")
public class OrderNumberEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer deptId;
	private Integer orderNum;
	private Integer orderTime;
	private LocalDate orderDay;
}
