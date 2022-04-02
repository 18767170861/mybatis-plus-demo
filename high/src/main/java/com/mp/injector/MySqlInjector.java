package com.mp.injector;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.mp.method.DeleteAllMethod;
@Component
public class MySqlInjector extends DefaultSqlInjector{

	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		//先调用父类添加的方法列表 再将自己的添加进去
		List<AbstractMethod> methodList = super.getMethodList(null);
		methodList.add(new DeleteAllMethod());
		
		//加入选装件
		//选择有参构造器 插入时可以排除某些字段
		//new InsertBatchSomeColumn();无参则插入所有字段
		methodList.add(new InsertBatchSomeColumn(
				t->!t.isLogicDelete() && !t.getColumn().equals("age")));
		
		//加入选装件
		//逻辑删除并附带字段填充功能
		methodList.add(new LogicDeleteByIdWithFill());
		
		//加入选装件
		//逻辑删除并附带字段填充功能 
		//new AlwaysUpdateSomeColumnById() 无参则更新所有字段
		methodList.add(new AlwaysUpdateSomeColumnById(t->!t.getColumn().equals("name")));
		
		return methodList;
	}	
}
