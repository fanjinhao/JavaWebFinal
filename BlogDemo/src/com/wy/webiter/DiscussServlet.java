package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.DiscussDao;
import com.wy.form.DiscussForm;
import com.wy.tool.Chinese;

public class DiscussServlet extends HttpServlet {

	private int method;

	private DiscussDao disussDao = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("32131");
		this.method = Integer.parseInt(request.getParameter("method"));
		System.out.println("32131");
		if (method == 0) {
			this.addDisuss(request, response);// 后台，添加公告内容
		}
		if (method == 1) {
			this.deleteDisuss(request, response);// 后台， 删除公告内容
		}
		if (method == 2) {
			this.updateDisuss(request, response);//	后台， 修改公告内容
		}
		
		
		

	}

//	后台， 修改评论内容
	public void updateDisuss(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		DiscussForm disussForm = new DiscussForm();
		disussDao = new DiscussDao();
		disussForm.setId(Integer.valueOf(request.getParameter("id")));
		disussForm.setDiscussTitle(Chinese.toChinese(request
				.getParameter("discussTitle")));
		disussForm.setDiscussContent(Chinese.toChinese(request
				.getParameter("discussContent")));
		if (disussDao.operationDiscuss("修改", disussForm)) {
			out
					.print("<script language=javascript>alert('修改公告信息成功！');window.location.href='back_DiscussSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('修改公告信息失败！');history.go(-1);</script>");
		}

		
	
	}
	
	
	//后台， 删除评论内容
	public void deleteDisuss(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		DiscussForm disussForm = new DiscussForm();
		disussDao = new DiscussDao();
		disussForm.setId(Integer.valueOf(request.getParameter("id")));
		if (disussDao.operationDiscuss("删除", disussForm)) {
			out
					.print("<script language=javascript>alert('删除公告信息成功！');window.location.href='back_DiscussSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('删除公告信息失败！');history.go(-1);</script>");
		}

	}

	// 后台，添加评论内容
	public void addDisuss(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DiscussForm disussForm = new DiscussForm();
		disussDao = new DiscussDao();
		disussForm.setDiscussTitle(Chinese.toChinese(request
				.getParameter("discussTitle")));
		disussForm.setDiscussContent(Chinese.toChinese(request
				.getParameter("discussContent")));
		disussForm.setDiscussTime(Chinese.toChinese(request
				.getParameter("discussTime")));
		String result = "添加公告失败！";
		if (disussDao.operationDiscuss("添加", disussForm)) {
			result = "添加公告成功！";
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_DiscussAdd.jsp");
		requestDispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
