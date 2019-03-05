package com.study.jsp03;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListenerEx implements ServletContextListener {
	
	public ContextListenerEx() {
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("어플리케이션 종료");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("어플리케이션 시작");
		
		//Ex#1
		ServletContext sc = event.getServletContext();
		sc.setAttribute("schedule", 1000);
	}
		



}
