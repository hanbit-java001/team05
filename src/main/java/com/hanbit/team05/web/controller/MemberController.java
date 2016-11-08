package com.hanbit.team05.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.MemberService;
import com.hanbit.team05.core.service.SecurityService;
import com.hanbit.team05.core.session.LoginRequired;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
import com.hanbit.team05.core.vo.MemberVO;

@Controller
public class MemberController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping("/api/member/join")
	@ResponseBody
	public Map doJoin(@RequestBody MemberVO member) {

		memberService.joinMember(member);

		Map result = new HashMap();
		result.put("success", "ȸ�����Կ� �����Ͽ����ϴ�.");

		return result;
	}

	@LoginRequired
	@RequestMapping("/member/mypage")
	public String myPage() {

		String path = "mypage";

		Session session = SessionHelper.getSession();

		if (session.getEmail().equals("admin")) {
			path = "admin";
		}

		return path;
	}

	@RequestMapping("/api/mypage/data")
	@ResponseBody
	public MemberVO getMember() {

		MemberVO result = memberService.getMember();
		return result;
	}

	@RequestMapping("/api/modify/data")
	@ResponseBody
	public Map modifyData(@RequestParam("password") String password) {
		return memberService.modifyData(password);
	}
}
