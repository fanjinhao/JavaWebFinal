package com.wy.webiter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.wy.dao.ConsumerDao;
import com.wy.form.ConsumerForm;
import com.wy.tool.Chinese;

public class ConsumerServlet extends HttpServlet {
	private ConsumerDao consumerDao = null;
	private int method;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			checkConsumer(request, response); // 用户登录操作
		}
		if (method == 1) {
			registerConsumer(request, response);// 用户注册操作
		}
		if (method == 2) {
			queryConsumerForm(request, response);// 后台操作中，对一个用户进行查询
		}
		if (method == 3) {
			deleteConsumerForm(request, response);// 后台操作中，对用户进行删除操作
		}
		if (method == 4) {
			queryConsumerHostForm(request, response); // 后台操作中，对博主的查询操作
		}
		if (method == 5) {
			updateConsumerHostForm(request, response); // 后台操作中，对博主信息的修改操作
		}
		if (method == 6) {
			front_updateConsumerForm(request, response); // 前台操作中，用户对登录用进行修改
		}

	}

	// 前台操作中，用户对登录用进行修改
	public void front_updateConsumerForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ConsumerForm form = new ConsumerForm();
		consumerDao = new ConsumerDao();
		form.setAccount(Chinese.toChinese(request.getParameter("account")));
		form.setPassword(Chinese.toChinese(request.getParameter("password")));
		form.setName(Chinese.toChinese(request.getParameter("name")));
		form.setSex(Chinese.toChinese(request.getParameter("sex")));
		form.setQQNumber(request.getParameter("QQnumber"));
		form.setMainPage(request.getParameter("mainPage"));
		form.setInterest(Chinese.toChinese(request.getParameter("interest")));
		form.setId(Integer.valueOf(request.getParameter("id")));
		form.setEMail(request.getParameter("eMail"));
		if (consumerDao.front_updateConsumerForm(form)) {
			out
					.print("<script language=javascript>alert('修改用户成功，请重新登录！');window.location.href='dealwith.jsp?sign=2';</script>");
		} else {
			out
					.print("<script language=javascript>alert('修改用户信息失败！');history.go(-1);</script>");
		}

	}

	// 后台操作中，对博主信息的修改操作
	public void updateConsumerHostForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		ConsumerForm form = new ConsumerForm();
		consumerDao = new ConsumerDao();
		form.setAccount(Chinese.toChinese(request.getParameter("account")));
		form.setPassword(Chinese.toChinese(request.getParameter("password")));
		form.setName(Chinese.toChinese(request.getParameter("name")));
		form.setSex(Chinese.toChinese(request.getParameter("sex")));
		form.setQQNumber(request.getParameter("QQnumber"));
		form.setMainPage(request.getParameter("mainPage"));
		form.setInterest(Chinese.toChinese(request.getParameter("interest")));
		form.setEMail(request.getParameter("eMail"));
		form.setManageLevel("高级");
		String result = "修改博主信息失败！";

		if (consumerDao.updateConsumerForm(form)) {
			result = "修改博主信息成功！";
		}

		request.setAttribute("result", result);
		request.setAttribute("form", consumerDao.getConsumerForm(form
				.getAccount()));

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_consumerSelectHostForm.jsp");
		requestDispatcher.forward(request, response);
	}

	// 后台操作中，对用户进行删除操作
	public void deleteConsumerForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		String account = Chinese.toChinese(request.getParameter("account"));
		consumerDao = new ConsumerDao();
		PrintWriter out = response.getWriter();
		if (consumerDao.deleteConsumerForm(account)) {
			out
					.print("<script language=javascript>alert('删除此用户成功，请重新进行查询！');window.location.href='back_consumerSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('删除用户信息失败！');history.go(-1);</script>");
		}
	
	}

	// 后台操作中，对博主的查询操作
	public void queryConsumerHostForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		consumerDao = new ConsumerDao();
		request.setAttribute("form", consumerDao.getConsumerForm("tsoft"));
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_consumerSelectHostForm.jsp");
		requestDispatcher.forward(request, response);

	}

	// 后台操作中，对一个用户进行查询
	public void queryConsumerForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		consumerDao = new ConsumerDao();
		String account = Chinese.toChinese(request.getParameter("account"));
		request.setAttribute("form", consumerDao.getConsumerForm(account));
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_consumerSelectForm.jsp");
		requestDispatcher.forward(request, response);
	}

	// 用户注册操作
	public void registerConsumer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		ConsumerForm form = new ConsumerForm();
		consumerDao = new ConsumerDao();
		form.setAccount(Chinese.toChinese(request.getParameter("account")));
		form.setPassword(Chinese.toChinese(request.getParameter("password")));
		form.setName(Chinese.toChinese(request.getParameter("name")));
		form.setSex(Chinese.toChinese(request.getParameter("sex")));
		form.setQQNumber(request.getParameter("QQnumber"));
		form.setMainPage(request.getParameter("mainPage"));
		form.setInterest(Chinese.toChinese(request.getParameter("interest")));
		form.setEMail(request.getParameter("eMail"));
		form.setManageLevel("普通");
		String result = "fail";
		if (consumerDao.getConsumerForm(form.getAccount()) == null) {
			if (consumerDao.addConsumerForm(form)) {
				request.setAttribute("form", consumerDao.getConsumerForm(form
						.getAccount()));
				result = "success";
			}
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("dealwith.jsp");
		requestDispatcher.forward(request, response);
	}

	// 用户登录操作
	public void checkConsumer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		String account = request.getParameter("account");
		consumerDao = new ConsumerDao();
		ConsumerForm consumerForm = consumerDao.getConsumerForm(account);
		if (consumerForm == null) {
			request.setAttribute("information", "您输入的用户名不存在，请重新输入！");
		} else if (!consumerForm.getPassword().equals(
				request.getParameter("password"))) {
			request.setAttribute("information", "您输入的登录密码有误，请重新输入！");
		} else {

			request.setAttribute("form", consumerForm);
		}
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("dealwith.jsp");
		requestDispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
