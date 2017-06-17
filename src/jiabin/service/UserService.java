package jiabin.service;

import java.util.List;

import jiabin.entity.PageBean;
import jiabin.entity.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean existUserWithNickName(String nickName);
	
	public User login(User user);
	
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	public Long getUserCount(User s_user);
	
	public void delete(User user);
	
	public User getUserById(int id);
	
	public User getUserByNickName(String nickName);
}
