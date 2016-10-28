package com.hanbit.team05.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.ConsultService;
import com.hanbit.team05.core.vo.ConsultVO;

@Controller
public class ConsultController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultController.class);

	@Autowired
	private ConsultService consultService;

	@RequestMapping("/api/add/consult")
	@ResponseBody
	public Map insertConsult(@RequestBody ConsultVO consult) {

		LOGGER.debug(consult.toString());

		consultService.addConsult(consult);

		Map result = new HashMap<>();
		result.put("success", "상담접수에 완료하였습니다.");

		return result;
	}
}
