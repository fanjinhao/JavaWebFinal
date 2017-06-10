package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.wy.form.ConsumerForm;
import com.wy.tool.JDBConnection;

public class ConsumerDao {
	private JDBConnection connection = null;

	private ConsumerForm consumerForm = null;

	public ConsumerDao() {
		connection = new JDBConnection();
	}

	// 以数据库编号为条件，修改用户信息
	public boolean front_updateConsumerForm(ConsumerForm form) {
		boolean flag = false;
		String sql = "update tb_consumer set account='" + form.getAccount()
				+ "',password='" + form.getPassword() + "',name='"
				+ form.getName() + "',sex='" + form.getSex() + "',QQNumber='"
				+ form.getQQNumber() + "',mainPage='" + form.getMainPage()
				+ "',interest='" + form.getInterest() + "',eMail='"
				+ form.getEMail() + "' where id='" + form.getId() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	// 更新用户操作

	public boolean updateConsumerForm(ConsumerForm form) {
		boolean flag = false;
		String sql = "update tb_consumer set account='" + form.getAccount()
				+ "',password='" + form.getPassword() + "',name='"
				+ form.getName() + "',sex='" + form.getSex() + "',QQNumber='"
				+ form.getQQNumber() + "',mainPage='" + form.getMainPage()
				+ "',interest='" + form.getInterest() + "',eMail='"
				+ form.getEMail() + "' where manageLevel='"
				+ form.getManageLevel() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	// 删除用户信息
	public boolean deleteConsumerForm(String account) {
		boolean flag = false;
		String sql = "delete from tb_consumer where account='" + account + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	// 添加用户信息
	public boolean addConsumerForm(ConsumerForm form) {
		boolean flag = false;
		String sql = "insert into tb_consumer values ('" + form.getAccount()
				+ "','" + form.getPassword() + "','" + form.getName() + "','"
				+ form.getSex() + "','" + form.getQQNumber() + "','"
				+ form.getMainPage() + "','" + form.getInterest() + "','"
				+ form.getEMail() + "','" + form.getManageLevel() + "')";

		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}
	
	public String getConsumerForm(Integer id) {
		String sql = "select * from tb_consumer where id='" + id
				+ "'";
		String name="";
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				name=rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	
	// 以用户名为查询条件，查询一组数据
	public ConsumerForm getConsumerForm(String account) {
		String sql = "select * from tb_consumer where account='" + account
				+ "'";
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				consumerForm = new ConsumerForm();
				consumerForm.setId(Integer.valueOf(rs.getString(1)));
				consumerForm.setAccount(rs.getString(2));
				consumerForm.setPassword(rs.getString(3));
				consumerForm.setName(rs.getString(4));
				consumerForm.setSex(rs.getString(5));
				consumerForm.setQQNumber(rs.getString(6));
				consumerForm.setMainPage(rs.getString(7));
				consumerForm.setInterest(rs.getString(8));
				consumerForm.setEMail(rs.getString(9));
				consumerForm.setManageLevel(rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consumerForm;
	}

	// 根据用户账号查询所有的数据
	public List getConsumerList(String manageLevel) {
		List list = new ArrayList();
		String sql = "select * from tb_consumer where manageLevel='"
				+ manageLevel + "'";
		try {
			ResultSet rs = connection.executeQuery(sql);
			while (rs.next()) {
				consumerForm = new ConsumerForm();
				consumerForm.setId(Integer.valueOf(rs.getString(1)));
				consumerForm.setAccount(rs.getString(2));
				consumerForm.setPassword(rs.getString(3));
				consumerForm.setName(rs.getString(4));
				consumerForm.setSex(rs.getString(5));
				consumerForm.setQQNumber(rs.getString(6));
				consumerForm.setMainPage(rs.getString(7));
				consumerForm.setInterest(rs.getString(8));
				consumerForm.setEMail(rs.getString(9));
				consumerForm.setManageLevel(rs.getString(10));
				list.add(consumerForm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}



}
