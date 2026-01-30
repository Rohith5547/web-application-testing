package com.dpt.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class login {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String DBusername;

	@Value("${spring.datasource.password}")
	private String DBpassword;

	private String userId = "";

	private String errorMessage="";
	
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView registerform()
	{
		ModelAndView mv=new ModelAndView("login");
		
		return mv;		
	}

}
