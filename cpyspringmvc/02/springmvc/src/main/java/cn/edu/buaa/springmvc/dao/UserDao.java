package cn.edu.buaa.springmvc.dao;

import org.springframework.stereotype.Repository;

import cn.edu.buaa.springmvc.bean.User;

@Repository
public class UserDao {

	public User selectByUsername(String username) {
		
		if (username.equals("admin")) {
			User user =  new User();
			user.setUsername("admin");
			user.setPassword("123");
			return user;
		}
		
		return null;
	}

}
