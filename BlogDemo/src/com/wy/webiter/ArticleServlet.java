package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.ArticleDao;
import com.wy.dao.ArticleTypeDao;
import com.wy.dao.RestoreDao;
import com.wy.form.ArticleForm;
import com.wy.form.ArticleTypeForm;
import com.wy.form.RestoreForm;
import com.wy.tool.Chinese;

public class ArticleServlet extends HttpServlet {

	private ArticleDao articleDao = null;

	private ArticleTypeDao articleTypeDao = null;

	private RestoreDao restoreDao = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			this.addArticleType(request, response);
		}
		if (method == 1) {
			this.deleteArticleType(request, response);
		}
		if (method == 2) {
			this.addArticle(request, response);
		}
		if (method == 3) {
			this.deleteArticle(request, response);
		}
		if (method == 4) {
			this.updateArticle(request, response);
		}
		if (method == 5) {
			this.headAddNumberArticle(request, response);
		}
		if (method == 6) {
			this.deleteRestore(request, response);
		}
		if (method == 7) {
			this.HeadAddRestore(request, response);
		}
	}

	public void HeadAddRestore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		restoreDao = new RestoreDao();
		RestoreForm restoreForm = new RestoreForm();
		restoreForm.setArticleId(Integer.valueOf(request.getParameter("articleId")));
		restoreForm.setReAccount(request.getParameter("accountId"));
		restoreForm.setReTitle(Chinese.toChinese(request.getParameter("reTitle")));
		restoreForm.setReContent(Chinese.toChinese(request.getParameter("reContent")));
		if (restoreDao.operationRestore("添加", restoreForm)) {
			out
					.print("<script language=javascript>alert('添加回复信息成功，请重新查询！');window.location.href='head_ArticleForm.jsp?id="+request.getParameter("articleId")+"';</script>");
		} else {
			out
					.print("<script language=javascript>alert('添加回复信息失败！');history.go(-1);</script>");
		}

	}

	public void deleteRestore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		restoreDao = new RestoreDao();
		RestoreForm restoreForm = new RestoreForm();
		restoreForm.setId(Integer.valueOf(request.getParameter("id")));
	
		if (this.restoreDao.operationRestore("删除", restoreForm)) {
			out
					.print("<script language=javascript>alert('删除回复成功，请重新查询！');window.location.href='back_RestoreSelect.jsp?id="
							+ request.getParameter("idd") + "';</script>");
		} else {
			out
					.print("<script language=javascript>alert('删除回复失败！');history.go(-1);</script>");
		}

	}

	// 增加访问次数
	public void headAddNumberArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArticleForm articleForm = new ArticleForm();
		articleDao = new ArticleDao();
		articleForm.setId(Integer.valueOf(request.getParameter("id")));
		articleDao.operationArticle("增加", articleForm);
		request.setAttribute("form", articleDao.queryArticleForm(Integer
				.valueOf(request.getParameter("id"))));
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("head_ArticleForm.jsp");
		requestDispatcher.forward(request, response);
	}

	public void updateArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleForm articleForm = new ArticleForm();
		articleForm.setId(Integer.valueOf(request.getParameter("id")));
		articleForm.setTypeId(Integer.valueOf(request.getParameter("typeId")));
		articleForm.setTitle(Chinese.toChinese(request.getParameter("title")));
		articleForm.setContent(Chinese.toChinese(request
				.getParameter("content")));
		articleDao = new ArticleDao();
		if (articleDao.operationArticle("修改", articleForm)) {
			out
					.print("<script language=javascript>alert('修改文章成功，请重新查询！');window.location.href='back_ArticleSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('修改文章失败！');history.go(-1);</script>");
		}
	}

	// 后台删除文章
	public void deleteArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleForm articleForm = new ArticleForm();
		articleForm.setId(Integer.valueOf(request.getParameter("id")));
		articleDao = new ArticleDao();
		if (articleDao.operationArticle("删除", articleForm)) {
			out
					.print("<script language=javascript>alert('删除文章成功，请重新查询！');window.location.href='back_ArticleSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('删除文章失败！');history.go(-1);</script>");
		}
	}

	// 后台添加文章
	public void addArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArticleForm articleForm = new ArticleForm();
		articleForm.setTypeId(Integer.valueOf(request.getParameter("typeId")));
		articleForm.setTitle(Chinese.toChinese(request.getParameter("title")));
		articleForm.setNumber(Integer.valueOf(request.getParameter("number")));
		articleForm.setContent(Chinese.toChinese(request
				.getParameter("content")));
		articleForm
				.setPhTime(Chinese.toChinese(request.getParameter("phTime")));
		articleDao = new ArticleDao();
		String result = "文章添加失败！";
		if (articleDao.operationArticle("添加", articleForm)) {
			result = "文章添加成功！";
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_ArticleAdd.jsp");
		requestDispatcher.forward(request, response);
	}

	// 后台删除文章类别
	public void deleteArticleType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		ArticleTypeForm.setId(Integer.valueOf(request.getParameter("id")));
		articleTypeDao = new ArticleTypeDao();
		if (articleTypeDao.operationArticleType("删除", ArticleTypeForm)) {
			out
					.print("<script language=javascript>alert('删除文章类别成功，请重新查询！');window.location.href='back_ArticleTypeSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('您需要将类别所在的文章删除,才可删除此类别！');history.go(-1);</script>");
		}

	}

	// 后台添加文章类别
	public void addArticleType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		ArticleTypeForm.setTypeName(Chinese.toChinese(request
				.getParameter("typeName")));
		ArticleTypeForm.setDescription(Chinese.toChinese(request
				.getParameter("description")));
		articleTypeDao = new ArticleTypeDao();
		if (articleTypeDao.operationArticleType("添加", ArticleTypeForm)) {
			out
					.print("<script language=javascript>alert('添加文章类别成功，请重新查询！');window.location.href='back_ArticleTypeSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('添加文章类别失败！');history.go(-1);</script>");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
