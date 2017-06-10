package com.wy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.wy.form.VoteForm;
import com.wy.tool.JDBConnection;

public class VoteDao {

	private JDBConnection connection = null;

	public VoteDao() {
		connection = new JDBConnection();
	}

	public boolean operationVote(String operation, VoteForm voteForm) {
		boolean flag = false;
		String sql = null;
		if (operation.equals("删除"))
			sql = "delete from tb_vote where id='" + voteForm.getId() + "'";
		if (operation.equals("添加"))
			sql = "insert into tb_vote values('" + voteForm.getVoteName()
					+ "','" + voteForm.getVoteNumber() + "')";
		if (operation.equals("投票"))
			sql = "update tb_vote set voteNumber=voteNumber+1 where id='"
					+ voteForm.getId() + "'";
		if (connection.executeUpdate(sql))
			flag = true;
		return flag;
	}

	public List queryVoteList() {
		List list = new ArrayList();
		String sql = "select * from tb_vote";
		ResultSet rs = connection.executeQuery(sql);
		VoteForm form = null;
		try {
			while (rs.next()) {
				form = new VoteForm();
				form.setId(Integer.valueOf(rs.getString(1)));
				form.setVoteName(rs.getString(2));
				form.setVoteNumber(Integer.valueOf(rs.getString(3)));
				list.add(form);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;

	}


}
