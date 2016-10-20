package com.hanbit.team05.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.team05.core.service.MemberService;
import com.hanbit.team05.core.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/api/member/login")
	@ResponseBody
	public boolean login(@RequestParam("mId") String email, @RequestParam("pwd") String pwd){

		return false;
	}

	@RequestMapping("/api/member/join")
	@ResponseBody
	public int join(@RequestBody MemberVO member) {
		int result = memberService.joinMember(member);
		if (result != 1) {
			throw new RuntimeException("error");
		}

		return result;
	}
}
