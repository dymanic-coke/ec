package com.spring.ec.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface UserController {
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
