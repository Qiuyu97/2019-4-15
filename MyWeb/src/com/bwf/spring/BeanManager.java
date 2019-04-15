package com.bwf.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanManager {

	private static ApplicationContext context
		= new ClassPathXmlApplicationContext("spring_config.xml");
	public static Object getBean(String beanId){
		return context.getBean(beanId);
	}
}
