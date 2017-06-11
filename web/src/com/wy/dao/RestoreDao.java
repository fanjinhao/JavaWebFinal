package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wy.form.RestoreForm;
import com.wy.tool.JDBConnection;

public class RestoreDao {
	private JDBConnection connection = null;

	public RestoreDao() {
		connection = new JDBConnection();
	}

	public boolean operationRestore(String operation, RestoreForm form) {
		boolean flag = false;
		String sql = "";
		if (operation.equals("添加"))
			sql = "insert into tb_restore values ('" + form.getArticleId()
					+ "','" + form.getReAccount() + "','" + form.getReTitle()
					+ "','" + form.getReContent() + "')";
		if (operation.equals("删除"))
			sql = "delete from tb_restore where id='" + form.getId() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	public List queryRestore(Integer articleId) {
		List list = new ArrayList();
		String sql = "select * from tb_restore where articleId='" + articleId
				+ "' order by id desc";
		RestoreForm form = null;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new RestoreForm();
				form.setId(rs.getInt(1));
				form.setArticleId(rs.getInt(2));
				form.setReAccount(rs.getString(3));
				form.setReTitle(rs.getString(4));
				form.setReContent(rs.getString(5));
				list.add(form);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;

	}

}
