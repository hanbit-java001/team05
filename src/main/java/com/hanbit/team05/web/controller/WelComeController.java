package com.hanbit.team05.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelComeController {

	@RequestMapping("/")
	public String index(){

		return "index";
	}
}
