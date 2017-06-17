package jiabin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jiabin.dao.BaseDAO;
import jiabin.entity.PageBean;
import jiabin.entity.User;
import jiabin.service.UserService;
import jiabin.util.StringUtil;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource
	private BaseDAO<User> baseDAO;
	
	@Override
	public void saveUser(User user) {
		baseDAO.merge(user);
	}

	@Override
	public boolean existUserWithNickName(String nickName) {
		String hql="select count(*) from User where nickName=?";
		long count=baseDAO.count(hql, new Object[]{nickName});
		if (count>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User login(User user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User u where u.nickName=? and u.password=?");
		param.add(user.getNickName());
		param.add(user.getPassword());
		return baseDAO.get(hql.toString(), param);
	}

	@Override
	public List<User> findUserList(User s_user, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		if (s_user!=null) {
			if (StringUtil.isNotEmpty(s_user.getNickName())) {
				hql.append(" and nickName like ?");
				param.add("%"+s_user.getNickName()+"%");
			}
			if (s_user.getType()>0) {
				hql.append(" and type = ?");
				param.add(s_user.getType());
			}
		}
		if (pageBean!=null) {
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public Long getUserCount(User s_user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from User");
		if (s_user!=null) {
			if (StringUtil.isNotEmpty(s_user.getNickName())) {
				hql.append(" and nickName like ?");
				param.add("%"+s_user.getNickName()+"%");
			}
			if (s_user.getType()>0) {
				hql.append(" and type = ?");
				param.add(s_user.getType());
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void delete(User user) {
		baseDAO.delete(user);
	}

	@Override
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

	@Override
	public User getUserByNickName(String nickName) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		if (StringUtil.isNotEmpty(nickName)) {
			hql.append(" and nickName = ?");
			param.add(nickName);
		}
		return baseDAO.get(hql.toString().replaceFirst("and", "where"), param);
	}

}
