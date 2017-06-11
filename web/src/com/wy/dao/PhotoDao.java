package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wy.form.PhotoForm;
import com.wy.tool.JDBConnection;

public class PhotoDao {
	private JDBConnection connection = null;

	public PhotoDao() {
		connection = new JDBConnection();
	}

	public boolean operationPhoto(String operation, PhotoForm photoForm) {
		boolean flag = false;
		String sql = null;
		if (operation.equals("É¾³ý"))
			sql = "delete from tb_photo where id='" + photoForm.getId() + "'";
		if (operation.equals("Ìí¼Ó"))
			sql = "insert into tb_photo values ('"
					+ photoForm.getPhotoAddress() + "','"
					+ photoForm.getPhotoDescription() + "','"
					+ photoForm.getPhtoTime() + "')";

		if (connection.executeUpdate(sql))
			flag = true;
		return flag;
	}

	public PhotoForm queryPhoto(Integer id) {
		PhotoForm form = null;
		String sql = "select * from tb_photo where id='" + id + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new PhotoForm();
				form.setId(Integer.valueOf(rs.getString(1)));
				form.setPhotoAddress(rs.getString(2));
				form.setPhotoDescription(rs.getString(3));
				form.setPhtoTime(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return form;
	}

	public List queryPhoto() {
		List list = new ArrayList();
		PhotoForm form = null;
		String sql = "select * from tb_photo order by id desc";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new PhotoForm();
				form.setId(Integer.valueOf(rs.getString(1)));
				form.setPhotoAddress(rs.getString(2));
				form.setPhotoDescription(rs.getString(3));
				form.setPhtoTime(rs.getString(4));
				list.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Integer MaxQueryID() {
		Integer maxID = 0;
		String sql = "select max(id) as id from tb_photo";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				maxID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxID;

	}

}
