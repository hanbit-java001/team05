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
		result.put("success", "회원가입에 성공하였습니다.");

		return result;

	}

	@LoginRequired
	@RequestMapping("/member/mypage")
	public String myPage(){

		return "mypage";
	}
}
