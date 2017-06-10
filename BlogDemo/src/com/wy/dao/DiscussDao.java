package com.wy.dao;

import com.wy.form.DiscussForm;
import com.wy.tool.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

;

public class DiscussDao {
	private JDBConnection connection = null;

	public DiscussDao() {
		connection = new JDBConnection();
	}

	public boolean operationDiscuss(String operation, DiscussForm disussForm) {
		boolean flag = false;
		String sql = null;
		if (operation.equals("É¾³ý"))
			sql = "delete from tb_discuss where id='" + disussForm.getId()
					+ "'";
		if (operation.equals("Ìí¼Ó"))
			sql = "insert into tb_discuss values ('"
					+ disussForm.getDiscussTitle() + "','"
					+ disussForm.getDiscussContent() + "','"
					+ disussForm.getDiscussTime() + "')";
		if (operation.equals("ÐÞ¸Ä"))
			sql = "update tb_discuss set discussTitle='"
					+ disussForm.getDiscussTitle() + "',discussContent='"
					+ disussForm.getDiscussContent() + "' where id='"
					+ disussForm.getId() + "'";
		
		
		if (connection.executeUpdate(sql))
			flag = true;
		return flag;
	}

	public List queryDiscuss() {
		List list = new ArrayList();
		DiscussForm form = null;
		String sql = "select * from tb_discuss order by id desc";
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				form = new DiscussForm();
				form.setId(Integer.valueOf(rs.getString(1)));
			
				
				form.setDiscussTitle(rs.getString(2));
				form.setDiscussContent(rs.getString(3));
				form.setDiscussTime(rs.getString(4));
				list.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	
	public DiscussForm queryDiscuss(Integer id) {
		DiscussForm form = null;
		String sql = "select * from tb_discuss where id='"+id+"'";
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				form = new DiscussForm();
				form.setId(Integer.valueOf(rs.getString(1)));
				form.setDiscussTitle(rs.getString(2));
				form.setDiscussContent(rs.getString(3));
				form.setDiscussTime(rs.getString(4));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return form;

	}
	
	
	

}
