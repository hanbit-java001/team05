package com.hanbit.team05.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public int joinMember(MemberVO memberVO) {
		return memberDAO.joinMember(memberVO);
	}

}
