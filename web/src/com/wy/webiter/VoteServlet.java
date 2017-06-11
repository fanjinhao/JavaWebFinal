package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.tool.Chinese;
import com.wy.dao.VoteDao;
import com.wy.form.VoteForm;

public class VoteServlet extends HttpServlet {

	private VoteDao voteDao = null;

	private int method;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			this.addVote(request, response);
		}
		if (method == 1) {
			this.deleteVote(request, response);
		}
	}



	// 后台-删除投票内容
	public void deleteVote(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		VoteForm voteForm = new VoteForm();
		voteDao = new VoteDao();
		voteForm.setId(Integer.valueOf(request.getParameter("id")));
		if (voteDao.operationVote("删除", voteForm)) {
			out
					.print("<script language=javascript>alert('删除此投票内容成功，请重新进行查询！');window.location.href='back_VoteSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('删除此投票内容失败！');history.go(-1);</script>");
		}
	}

	// 后台－添加投票内容
	public void addVote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VoteForm voteForm = new VoteForm();
		voteDao = new VoteDao();
		voteForm.setVoteName(Chinese
				.toChinese(request.getParameter("voteName")));
		voteForm.setVoteNumber(0);
		String result = "添加投票内容失败!";
		if (voteDao.operationVote("添加", voteForm)) {
			result = "添加投票内容成功!";
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_VoteAdd.jsp");
		requestDispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
