package com.hanbit.team05.core.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
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

	public MemberVO getMember() {
		Session session = SessionHelper.getSession();
		String email = session.getEmail();

		return memberDAO.selectMember(email);
	}

	public Map modifyData(String password) {

		Session session = SessionHelper.getSession();
		String email = session.getEmail();

		Map data = new HashMap<>();
		data.put("password", securityService.encryptPassword(password));
		data.put("email", email);

		int dataModifyResult = memberDAO.modifyMember(data);

		if (dataModifyResult != 1) {
			throw new RuntimeException("��й�ȣ�� �����ϴµ��� ������ �߻��Ͽ����ϴ�. �ٽýõ����ּ���.");
		}

		Map result = new HashMap<>();
		result.put("success", "���������� �����Ͽ����ϴ�.");

		return result;
	}

}
