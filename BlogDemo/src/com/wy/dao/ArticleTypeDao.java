package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wy.form.ArticleTypeForm;
import com.wy.tool.JDBConnection;

public class ArticleTypeDao {
	private JDBConnection connection = null;

	public ArticleTypeDao() {
		connection = new JDBConnection();
	}

	public boolean operationArticleType(String operation, ArticleTypeForm form) {
		boolean flag = false;
		String sql = null;
		if (operation.equals("Ìí¼Ó"))
			sql = "insert into tb_articleType values ('" + form.getTypeName()
					+ "','" + form.getDescription() + "')";
		if (operation.equals("É¾³ý"))
			sql = "delete from tb_articleType where id='" + form.getId() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	
	
	public String queryArticleTypeName(Integer id) {
		String typeName = null;
		String sql = "select * from tb_articleType where id='" + id + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				typeName = rs.getString("typeName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeName;
	}

	public List queryArticleType() {
		List list = new ArrayList();
		ArticleTypeForm form = null;
		String sql = "select * from tb_articleType";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new ArticleTypeForm();
				form.setId(rs.getInt(1));
				form.setTypeName(rs.getString(2));
				form.setDescription(rs.getString(3));
				list.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
