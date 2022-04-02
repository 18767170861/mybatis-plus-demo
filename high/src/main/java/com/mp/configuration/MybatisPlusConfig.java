package com.mp.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

@Configuration
public class MybatisPlusConfig {
	public static ThreadLocal<String> myTableName = new ThreadLocal<String>();
	
	//逻辑删除注入器
	//3.1.1不需要注册此bean 防止3.1.1版本一下找不到此类
//	@Bean
//	public ISqlInjector sqlInjector() {
//		return new LogicSqlInjector(); 
//	}
	//乐观锁插件
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
	//性能分析插件
	//一般生产环境事不开启的  只在开发和测试环境中开启
	@Profile({"dev","test"})
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		//将输出信息格式化
		performanceInterceptor.setFormat(true);
		//超过5毫秒就停止运行
		//performanceInterceptor.setMaxTime(5L);
		return performanceInterceptor; 
	}
	
	//分页插件(实现多租户解析器)
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		ArrayList<ISqlParser> sqlParserList = new ArrayList<ISqlParser>();
		
		// 动态表名SQL解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String,ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        // Map的key就是需要替换的原始表名
        tableNameHandlerMap.put("user", new ITableNameHandler() {
			@Override
			public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
				// 自定义表名规则，或者从配置文件、request上下文中读取	
				System.out.println("metaObject:" + metaObject);
				System.out.println("sql:" + sql);
				System.out.println("tableName:" + tableName);
				System.out.println("myTableName.get():" + myTableName.get());
				return myTableName.get();
			}
		});
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        
        //创建租户SQL解析器
//		TenantSqlParser tenantSqlParser = new TenantSqlParser();
//		tenantSqlParser.setTenantHandler(new TenantHandler() {
//			
//			@Override
//			public String getTenantIdColumn() {
//				// 表中的字段名 不是类的属性名
//				return "manager_id";
//			}
//			
//			@Override
//			public Expression getTenantId() {
//				// 租户信息.租户实际传进来的manager_id值
//				return new LongValue(1088248166370832385L);
//			}
//			
//			@Override
//			public boolean doTableFilter(String tableName) {
//				// 哪些加上租户信息 哪些不加
//				if("role".equals(tableName)) {
//					return true;
//				}
//				return false;
//			}
//		});
//		sqlParserList.add(tenantSqlParser);
		
//		paginationInterceptor.setSqlParserList(sqlParserList);
//		paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//			//特定sql过滤
//			@Override
//			public boolean doFilter(MetaObject metaObject) {
//				// TODO Auto-generated method stub
//				MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
//				if("com.mp.dao.UserMapper.selectById".equals(mappedStatement.getId())) {
//					//不增加租户信息
//					return true;
//				}
//				return false;
//			}
//		});
		return paginationInterceptor;
	}
	
	
}
