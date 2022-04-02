package com.mp.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@TableName("mp_user")声明此bean数据库对应名称
//继承Model抽象接口 实现AR模式 查询一个是如果有多个时则取第一个
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User>{
	private static final long serialVersionUID = 1L;
	//主键
	//@TableId表示此列为主键
	//type=IdType.AUTO表示主键策略为自增
	//type=IdType.NONE表示主键策略为默认配置,id根据雪花算法生成
	//type=IdType.UUID表示主键策略为String类型自生成
	//type=IdType.ID_WORKER_STR表示主键策略为雪花算法字符串类型
	//@TableId(type=IdType.AUTO)
	private Long id;
	//姓名		
	//@TableField("name")指定此列对应表为name
	//@TableField(condition=SqlCondition.LIKE) 
	//当此类作为查询条件类时 标识此列为 like name
	//@TableField(strategy=FieldStrategy.NOT_EMPTY) 字段计入sql语句策略为 为空或者weinull不加入sql语句
	@TableField(strategy=FieldStrategy.NOT_EMPTY)
	private String name;
	//年龄
	private Integer age;
	//邮箱
	private String email;
	//直属上级
	private Long managerId;
	//创建时间
	private LocalDateTime createTime;
	//备注
	//transient标识的成员变量不参与序列化过程
	//static标识的成员变量不会被mybatis-plus加入到sql语句中
	//@TableField(exist=false)标识此字段不是数据库调动你的字段
	@TableField(exist=false)
	private String remark;
	
}
