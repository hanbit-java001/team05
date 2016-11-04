package com.hanbit.team05.core.application;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hanbit.team05.core.service.EmailService;
import com.hanbit.team05.core.vo.EmailVO;

public class ApplicationSimple {

//	public static void main(String[] args) {
//
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"spring/applicationContext-core.xml",
//				"spring/applicationContext-dao.xml",
//				"spring/applicationContext-mail.xml");
//
//		EmailService eamilService = context.getBean(EmailService.class);
//
//		EmailVO vo = new EmailVO();
//		vo.setFromAddress("iiedby2500");
//		vo.setToAddress("iiedby2500@naver.com");
//		vo.setSubject("test");
//		vo.setText("test");
//
//		Map result = eamilService.sendEmail(vo);
//
//		System.out.println(result.get("success"));
//	}
}
