package com.hanbit.team05.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hanbit.team05.core.service.ConsultService;

@Controller
public class ConsultController {

	private static final Logger logger = LoggerFactory.getLogger(ConsultController.class);

	@Autowired
	private ConsultService consultService;
	
}
