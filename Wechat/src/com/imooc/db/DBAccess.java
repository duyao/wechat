package com.imooc.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author dy
 * 访问数据库类
 *
 */
public class DBAccess {
	
	public SqlSession getSqlSession() throws IOException{
		
		//1.通过配置文件获取数据库连接
		//路径名从src的根路径开始算起,路径应是/
		Reader reader = Resources.getResourceAsReader("com/imooc/config/Configuration.xml");
		//2.通过配置信息构建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//3.通过SqlSessionFactory打开一个数据库库会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession;
		
		
		
	}

	
	
}
