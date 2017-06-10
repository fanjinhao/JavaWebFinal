package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.wy.dao.PhotoDao;
import com.wy.form.PhotoForm;

public class PhotoSerlvet extends HttpServlet {

	private int method;

	private PhotoDao photoDao = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			this.addPhoto(request, response); // 上传图片
		}
		if (method == 1) {
			this.deletePhoto(request, response); // 删除图片
		}

	}

	// 删除图片
	public void deletePhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		photoDao = new PhotoDao();
		Integer id = Integer.valueOf(request.getParameter("id"));
		String photoDir = request.getRealPath(photoDao.queryPhoto(id)
				.getPhotoAddress());
		java.io.File file = new java.io.File(photoDir);
		PhotoForm photoForm = new PhotoForm();
		photoForm.setId(id);
		if (photoDao.operationPhoto("删除", photoForm)) {
			file.delete();
			out
					.print("<script language=javascript>alert('删除图片成功，请重新查询！');window.location.href='back_PhotoSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('修改用户信息失败！');history.go(-1);</script>");
		}

	}

	public void addPhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		photoDao = new PhotoDao();
		PhotoForm photoForm = new PhotoForm();
		com.jspsmart.upload.SmartUpload su = new com.jspsmart.upload.SmartUpload();

		Integer maxID = 0;
		if (photoDao.MaxQueryID() != null) {
			maxID = photoDao.MaxQueryID();
		}
		String result = "上传的照片格式和大小有问题,上传照片失败!";
		String type = null;
		String imageType[] = { "JPG", "jpg", "gif", "bmp", "BMP" };
		String filedir = "file/";
		long maxsize = 2 * 1024 * 1024; // 设置每个上传文件的大小，为2MB
		try {
			su.initialize(this.getServletConfig(), request, response);
			su.setMaxFileSize(maxsize); // 限制上传文件的大小
			su.upload(); // 上传文件
			Files files = su.getFiles(); // 获取所有的上传文件
			for (int i = 0; i < files.getCount(); i++) { // 逐个获取上传的文件
				File singlefile = files.getFile(i);
				type = singlefile.getFileExt();

				for (int ii = 0; ii < imageType.length; ii++) {
					if (imageType[ii].equals(type)) {
						if (!singlefile.isMissing()) { // 如果选择了文件
							String photoTime = su.getRequest().getParameter(
									"phtoTime");
							String photoDescription = su.getRequest()
									.getParameter("photoDescription");

							photoForm.setPhtoTime(photoTime);

							photoForm.setPhotoDescription(photoDescription);
							filedir = filedir + maxID + "."
									+ singlefile.getFileExt();
							photoForm.setPhotoAddress(filedir);
							if (photoDao.operationPhoto("添加", photoForm)) {
								singlefile.saveAs(filedir, File.SAVEAS_VIRTUAL);
								result = "上传照片成功!";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_PhotoInsert.jsp");
		requestDispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
