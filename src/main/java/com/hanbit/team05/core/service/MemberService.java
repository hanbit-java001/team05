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
			throw new RuntimeException("�̹� ������� �̸����Դϴ�.");
		}

		String password = securityService.encryptPassword(memberVO.getPassword());
		memberVO.setPassword(password);

		int result = memberDAO.joinMember(memberVO);

		if (result != 1) {
			throw new RuntimeException("ȸ������ ���߿� ������ �߻��Ͽ����ϴ�. ��� �� �õ����ּ���.");
		}

		return result;
	}

}
