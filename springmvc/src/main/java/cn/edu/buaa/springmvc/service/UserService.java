package cn.edu.buaa.springmvc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.buaa.springmvc.bean.User;
import cn.edu.buaa.springmvc.dao.UserDao;

@Service
public class UserService {
	
	// 默认按名称进行装配
	@Resource
	private UserDao dao;

	public User doLogin(String username, String password) throws Exception {
		
		if (username == null || username.equals("")) {
			throw new Exception("用户名不能为空");
		}
		
		if (password == null || password.equals("")) {
			throw new Exception("密码不能为空");
		}
		
		User user = dao.selectByUsername(username);
		if (user == null) {
			throw new Exception("用户名不存在");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误");
		}
		
		return user;
	}
	
}
