package com.hanbit.team05.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.AdminService;
import com.hanbit.team05.core.session.LoginRequired;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
import com.hanbit.team05.core.vo.ConsultVO;

@Controller
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@LoginRequired
	@RequestMapping("/admin/page")
	public String admin() {
		String path = "";
		Session session = SessionHelper.getSession();

		path = "index";

		if (session.getEmail() != null && session.getEmail().equals("admin")) {
			path = "admin";
		}
		return path;
	}

	@RequestMapping("/api/admin/consult")
	@ResponseBody
	public Map getConsult(@RequestParam("pageNum") int pageNum, @RequestParam("flag") String flag) {

		return adminService.getConsult(pageNum, flag);
	}
}
