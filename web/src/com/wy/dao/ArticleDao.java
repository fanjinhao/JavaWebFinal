package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wy.form.ArticleForm;
import com.wy.tool.JDBConnection;

public class ArticleDao {
	private JDBConnection connection = null;

	private ArticleForm articleForm = null;

	public ArticleDao() {
		connection = new JDBConnection();
	}

	public boolean operationArticle(String operation, ArticleForm form) {
		boolean flag = false;
		String sql = null;
		if (operation.equals("添加"))
			sql = "insert into tb_article values ('" + form.getTypeId() + "','"
					+ form.getTitle() + "','" + form.getContent() + "','"
					+ form.getPhTime() + "','" + form.getNumber() + "')";
		if (operation.equals("修改"))
			sql = "update tb_article set typeID='" + form.getTypeId()
					+ "',title='" + form.getTitle() + "',content='"
					+ form.getContent() + "' where id='" + form.getId() + "'";
		if (operation.equals("删除"))
			sql = "delete from tb_article where id='" + form.getId() + "'";
		if (operation.equals("增加"))
			sql = "update tb_article set number=number+1 where id='"
					+ form.getId() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	public List queryArticle(Integer typeId) {
		List list = new ArrayList();
		String sql = null;
		if (typeId == null)
			sql = "select * from tb_article";
		else
			sql = "select * from tb_article where typeID='" + typeId
					+ "' order by id desc";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				articleForm = new ArticleForm();
				articleForm.setId(rs.getInt(1));
				articleForm.setTypeId(rs.getInt(2));
				articleForm.setTitle(rs.getString(3));
				articleForm.setContent(rs.getString(4));
				articleForm.setPhTime(rs.getString(5));
				articleForm.setNumber(rs.getInt(6));
				list.add(articleForm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArticleForm queryArticleForm(Integer id) {
		String sql = "select * from tb_article where id='" + id + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				articleForm = new ArticleForm();
				articleForm.setId(rs.getInt(1));
				articleForm.setTypeId(rs.getInt(2));
				articleForm.setTitle(rs.getString(3));
				articleForm.setContent(rs.getString(4));
				articleForm.setPhTime(rs.getString(5));
				articleForm.setNumber(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.operationArticle("增加", articleForm);
		return articleForm;
	}

}
