package com.imooc.service;

import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.MessageDao;

/**
 * @author dy
 * 删除一条
 * 还要转换类型
 *
 */
public class MaintainService {
	public void DeleteOne(String id){
		if(id != null && !"".equals(id)){
			MessageDao messageDao = new MessageDao();
			messageDao.DeleteOne(Integer.valueOf(id));

		}
	}
	
	public void DeleteBatch(String [] idStrings){
		List< Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < idStrings.length; i++) {
			System.out.println(idStrings[i]);
			list.add(Integer.valueOf(idStrings[i]));
			
		}
		MessageDao messageDao = new MessageDao();
		messageDao.DeleteBatch(list);

	}

}
