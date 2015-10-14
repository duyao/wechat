package com.imooc.service;

import com.imooc.until.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;
import com.imooc.db.DBAccess;

/**
 * @author dy
 * 查询列表服务， 所有的services都是用来处理乱码，转型的工作的
 *
 */
public class QueryService {
	public List<Message> queryMessageList(String command, String description)
			throws UnsupportedEncodingException {
		if (command != null && "".equals(command)) {
			String newcommand = new String(command.getBytes("ISO-8859-1"),
					"utf-8");
			System.out.println("new="+newcommand);
		}
		if (description != null && "".equals(description)) {
			String newdescription = new String(
					description.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(",newd="+newdescription);

		}
		MessageDao messageDao = new MessageDao();
//		System.out.println("newc=" + newcommand + ",newd=" + newdescription);
		return messageDao.queryMessageList(command, description);
	}
	
	
	/**
	 * @param command 传入查询命令
	 * 
	 * @return 返回多条或者一条结果
	 */
	public String queryByCommand(String command){
		MessageDao messageDao = new MessageDao();
		List<Message> list = new ArrayList<Message>();
		if(Iconst.HELP_COMMAND_STRING.equals(command)){
			list = messageDao.queryMessageList(null, null);
			//帮助命令，显示所有语句
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				if(i != 0){
					stringBuilder.append("<br/>");
				}
				
				stringBuilder.append("通过输入"+list.get(i).getCommand());
				stringBuilder.append("，可以获得"+list.get(i).getDescription());
			}
			return stringBuilder.toString();
		}
		
		//普通的通过命令查询功能
		list = messageDao.queryMessageList(command, null);
		return list.get(0).getDescription();
		
		
		
		
		
	}

}
