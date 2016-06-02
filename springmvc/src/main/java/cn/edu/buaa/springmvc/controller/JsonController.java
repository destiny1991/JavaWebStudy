package cn.edu.buaa.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.springmvc.bean.User;

// 测试：http://localhost:8080/springmvc/getJson.html

@Controller
public class JsonController {
	
	@ResponseBody
	@RequestMapping("/getJson")
	public User getUserInfo() {
		User user = new User();
		user.setPassword("123");
		user.setUsername("jsontest");
		return user;
	} 
	
}
