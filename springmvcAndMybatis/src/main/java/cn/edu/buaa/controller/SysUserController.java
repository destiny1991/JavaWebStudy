package cn.edu.buaa.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.buaa.entity.SysUser;
import cn.edu.buaa.service.ISysUserService;

@Controller
public class SysUserController {
	
	@Resource
	private ISysUserService service;
	
	@RequestMapping("/jdbc/all")
	public void selectAll() {
		List<SysUser> userList = service.selectAllWithJDBC();
		for(SysUser user : userList) {
			System.out.println(user);
		}
	}
	
	@RequestMapping("/jdbc/select/{id}")
	public void selectById(@PathVariable Integer id) {
		SysUser user = service.selectByIdWithJDBC(id);
		if(user != null) {
			System.out.println(user);
		} else {
			System.out.println("not found user with id : " + id);
		}
	}
	
	@RequestMapping("/jdbc/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteByIdWithJDBC(id);
		System.out.println("jdbc delete success");
	}
	
	@RequestMapping("/jdbc/save")
	public void save(@RequestParam String uName, @RequestParam Integer uAge) {
		service.saveWithJDBC(uName, uAge);
		System.out.println("jdbc save success");
	}
}
