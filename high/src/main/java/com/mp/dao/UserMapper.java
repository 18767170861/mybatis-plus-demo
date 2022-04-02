package com.mp.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mp.entity.User;
public interface UserMapper extends BaseMapper<User>{
	@SqlParser(filter=true)
	@Select("select * from user ${ew.customSqlSegment}")
	List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

	//自定义sql注入器 
	int deleteAll();
	
	//加入选装件批量插入
	int insertBatchSomeColumn(List<User> list);
	
	//选装件：根据id逻辑删除数据并带字段填充功能：比如再逻辑删除时指定删除人是谁
	int deleteByIdWithFill(User entity);
	
	//选装件：根据id更新固定的某些字段
	int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) User entity);
}
