package com.mp.mpcomponent;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		boolean hasSetter = metaObject.hasSetter("createTime");
		if(hasSetter) {
			System.out.println("insertFill---");
			setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
			//也可以setFieldValByName(fieldName, fieldVal, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object fieldValByName = getFieldValByName("updateTime", metaObject);
		System.out.println("fieldValByName:" + fieldValByName);
		//如果更新时 user.setUpdateTime(LocalDateTime.now()); 则不赋值
		if(fieldValByName == null) {
			System.out.println("updateFill---");
			setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
			//也可以setFieldValByName(fieldName, fieldVal, metaObject);
		}
	}

}
