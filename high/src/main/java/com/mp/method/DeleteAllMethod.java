package com.mp.method;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class DeleteAllMethod extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		//执行的sql
		String sql = "delete from " + tableInfo.getTableName() + " where id = '1094592041087729666'";
		System.out.println("sql:" + sql);
		//mapper接口方法名
		String method = "deleteAll";
		SqlSource createSqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return addDeleteMappedStatement(mapperClass, method, createSqlSource);
	}

}
