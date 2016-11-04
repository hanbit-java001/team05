package com.hanbit.team05.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.EmailService;

@Controller
public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping("/api/send/email")
	@ResponseBody
	public Map sendEmail(@RequestParam("toAddress") String toAddress, @RequestParam("text") String message,
			@RequestParam("consultId") int consultId) {

		return emailService.sendEmail(toAddress, message, consultId);
	}

	@RequestMapping("/api/send/findPw")
	@ResponseBody
	public Map findSendEmail(@RequestParam("toAddress") String toAddress, @RequestParam("name") String name){

		return emailService.findEmail(toAddress, name);
	}

}
