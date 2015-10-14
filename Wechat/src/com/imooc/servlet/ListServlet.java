package com.imooc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.imooc.bean.Message;
import com.imooc.service.QueryService;


/**
 * 列表页面初始化和查询控制
 */
@WebServlet("/List.action")
public class ListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接受页面的值
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		// 向页面传值
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		System.out.println("ggg"+command+description);
		QueryService listService = new QueryService();
		List< Message> messageList = listService.queryMessageList(command, description);
		// 查询消息列表并传给页面
		request.setAttribute("messageList", messageList);
		// 向页面跳转
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}