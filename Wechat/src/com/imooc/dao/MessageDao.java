package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import com.imooc.servlet.DeleteBatchServlet;
import com.imooc.servlet.DeleteOne;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
	


	/**
	 * @param command
	 * @param description
	 * @return 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command,String description){
		 
		DBAccess dbAccess = new DBAccess();
		//sqlsession需要关闭
		SqlSession sqlSession = null;
		List<Message> list = new ArrayList<Message>();
		try {
			 sqlSession = dbAccess.getSqlSession();
			 //通过sqlsession查询
			 //sqlSession.selectList是返回结果集,只能带一个参数
			 //因此command与description不能同时传入，因此可以用Message传递
			 Message message = new Message();
			 message.setCommand(command);
			 message.setDescription(description);
			 list = sqlSession.selectList("Message.queryMessageList",message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
			
		}
		
		return list;
	}
	
	/**
	 * @param id删除的id号码
	 * 单条删除
	 */
	public void DeleteOne (int id){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteOne", id);
			//mybatis增删改是非事务的，必须进行提交才能在数据库中实现
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
			
		}
		
		
	}

	
	//public void DeleteBatch(@Param("idList") List<Integer> idList){
	/**
	 * @param list批量删除的id号
	 */
	public void DeleteBatch(List<Integer> list){

		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteBatch", list);
//			 Map<String, Object> map = new HashMap<String, Object>();  
//			 map.put("idList", idList); 
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
			
		}
	}
	

	
	
	
	
	
	
	
	
	
	/**
	 * 根据查询条件查询消息列表
	 */
//	public List<Message> queryMessageList(String command,String description) {
//		List<Message> messageList = new ArrayList<Message>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message","root","root");
//			StringBuilder sql = new StringBuilder("select ID a,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1");
//			List<String> paramList = new ArrayList<String>();
//			if(command != null && !"".equals(command.trim())) {
//				sql.append(" and COMMAND=?");
//				paramList.add(command);
//			}
//			if(description != null && !"".equals(description.trim())) {
//				sql.append(" and DESCRIPTION like '%' ? '%'");
//				paramList.add(description);
//			}
//			PreparedStatement statement = conn.prepareStatement(sql.toString());
//			for(int i = 0; i < paramList.size(); i++) {
//				statement.setString(i + 1, paramList.get(i));
//			}
//			ResultSet rs = statement.executeQuery();
//			
//			while(rs.next()) {
//				Message message = new Message();
//				messageList.add(message);
//				message.setId(rs.getString("a"));
//				message.setCommand(rs.getString("COMMAND"));
//				message.setDescription(rs.getString("DESCRIPTION"));
//				message.setContent(rs.getString("CONTENT"));
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return messageList;
//	}
}
