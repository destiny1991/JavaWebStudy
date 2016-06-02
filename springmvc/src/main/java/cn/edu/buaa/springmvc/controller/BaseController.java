package cn.edu.buaa.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.edu.buaa.springmvc.exception.MyException;

public class BaseController {
	
	@ExceptionHandler(MyException.class)
	public String handleException(Exception ex, HttpServletRequest request) {
		request.setAttribute("error", ex.getMessage());
		return "/WEB-INF/jsp/exception.jsp";
	}
	
}
