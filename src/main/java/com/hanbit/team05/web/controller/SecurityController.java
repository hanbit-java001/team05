package com.hanbit.team05.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.SecurityService;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
import com.hanbit.team05.core.vo.MemberVO;

@Controller
public class SecurityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private SecurityService securitySerivce;

	@RequestMapping("/api/security/login")
	@ResponseBody
	public Map doLogin(@RequestParam("mEamil") String email, @RequestParam("mPassword") String password){

		MemberVO member = securitySerivce.getValidate(email, password);

		Session session = SessionHelper.getSession();
		session.setLoggedIn(true);
		session.setMemberId(member.getMemberId());
		session.setName(member.getName());
		session.setEmail(email);

		Map result = new HashMap<>();
		result.put("mName", member.getName());

		return result;
	}

	@RequestMapping(value="api/security/isLoggedIn",method=RequestMethod.GET)
	@ResponseBody
	public Map isLoggedIn(){

		Session session = SessionHelper.getSession();
		Map result = new HashMap<>();

		result.put("name", session.getName());
		result.put("email", session.getEmail());

		if (!session.isLoggedIn()) {
			result.put("name", "");
			result.put("email", "");
		}

		return result;
	}

	@RequestMapping("api/security/logout")
	@ResponseBody
	public void logout(HttpServletResponse response) throws IOException {

		Session session = SessionHelper.getSession();
		session.logout();

		response.sendRedirect("/");
	}
}
