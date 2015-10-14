package com.imooc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.bean.Message;
import com.imooc.service.MaintainService;
import com.imooc.service.QueryService;

/**
 * Servlet implementation class DeleteOne
 */
@WebServlet("/DeleteOne.action")
public class DeleteOne extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接受页面的值
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		// 向页面传值
		request.setAttribute("command", command);
		request.setAttribute("description", description);
	
		//删除一条信息
		String id = request.getParameter("id");
		MaintainService deleteOneService = new MaintainService();
		deleteOneService.DeleteOne(id);
		
		// 向页面跳转
		request.getRequestDispatcher("/List.action").forward(
				request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
