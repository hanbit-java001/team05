package com.hanbit.team05.core.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
import com.hanbit.team05.core.vo.MemberVO;

@Service
public class SecurityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

	@Autowired
	private MemberDAO memberDAO;

	private static PasswordEncoder encoder = new BCryptPasswordEncoder(4);

	public String encryptPassword(String password){
		return encoder.encode(password);
	}

	public boolean matchPassword(String rawPassword, String encryptedPassword){
		return encoder.matches(rawPassword, encryptedPassword);
	}

	public MemberVO getValidate(String email, String password){

		MemberVO member = memberDAO.selectMember(email);

		if(member == null){
			throw new RuntimeException("���̵� �Ǵ� ��й�ȣ�� ��ġ�����ʽ��ϴ�.");
		}

		String encryptedPassword = member.getPassword();

		if (!matchPassword(password, encryptedPassword)){
			throw new RuntimeException("���̵� �Ǵ� ��й�ȣ�� ��ġ�����ʽ��ϴ�.");
		}
		return member;
	}

	public Map checkPwd(String password) {

		Session session = SessionHelper.getSession();
		String email = session.getEmail();

		MemberVO member = memberDAO.selectMember(email);

		if (member == null) {
			throw new RuntimeException("���� ����, �α����� �ٽ����ּ���.");
		}

		String encryptedPassword = member.getPassword();

		if (!matchPassword(password, encryptedPassword)){
			throw new RuntimeException("��й�ȣ�� ��ġ�����ʽ��ϴ�.");
		}

		Map result = new HashMap<>();
		result.put("success", "���������� �����Ͽ����ϴ�.");

		return result;
	}
}
