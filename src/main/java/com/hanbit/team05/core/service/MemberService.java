package com.hanbit.team05.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private SecurityService securityService;

	@Transactional
	public int joinMember(MemberVO memberVO) {

		int emailCount = memberDAO.emailCount(memberVO.getEmail());

		if (emailCount > 0) {
			throw new RuntimeException("이미 사용중인 이메일입니다.");
		}

		String password = securityService.encryptPassword(memberVO.getPassword());
		memberVO.setPassword(password);

		int result = memberDAO.joinMember(memberVO);

		if (result != 1) {
			throw new RuntimeException("회원가입 도중에 에러가 발생하였습니다. 잠시 후 시도해주세요.");
		}

		return result;
	}

}
