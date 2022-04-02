package com.mp.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Data
public class User extends Model<User>{
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//姓名		
	private String name;
	//年龄
	@TableField(fill=FieldFill.UPDATE)
	private Integer age;
	//邮箱
	private String email;
	//直属上级
	private Long managerId;
	//创建时间
	@TableField(fill=FieldFill.INSERT)
	private LocalDateTime createTime;
	//修改时间
	@TableField(fill=FieldFill.UPDATE)
	private LocalDateTime updateTime;
	//版本
	@Version
	private Integer version;
	//逻辑删除标识（0.未删除 1.已删除）
	@TableLogic()
	@TableField(select = false)
	private Integer deleted;
	
}
