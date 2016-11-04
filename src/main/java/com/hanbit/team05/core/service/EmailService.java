package com.hanbit.team05.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.ConsultDAO;
import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.vo.EmailVO;
import com.hanbit.team05.core.vo.MemberVO;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private ConsultDAO consultDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private SecurityService securityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	public Map sendEmail(String toAddress, String replyMessage, int consultId) {
		try {
			Map data = new HashMap<>();
			data.put("content", replyMessage);
			data.put("consultId", consultId);

			int modifyResult = consultDAO.modifyConsult(data);

			LOGGER.debug(modifyResult + " " + data);

			if (modifyResult != 1) {
				throw new RuntimeException("�ٽ� �õ����ּ���.");
			}

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("iiedby2500@naver.com");
			message.setTo(toAddress);
			message.setSubject("�ȳ��ϼ��� ��ī�������ܰ��Դϴ�.");
			message.setText(replyMessage);
			mailSender.send(message);

			Map result = new HashMap<>();
			result.put("success", "�̸����� ���������� �����µ� �����Ͽ����ϴ�.");

			return result;

		} catch (Exception e) {
			throw new RuntimeException("�̸��ϸ� �����µ� ������ �߻��Ͽ����ϴ�. �ٽ� �õ����ּ���.");
		}
	}

	public Map findEmail(String toAddress, String name) {


		MemberVO member = memberDAO.selectMember(toAddress);

		if (member == null) {
			throw new RuntimeException("�������� �ʴ� ȸ���Դϴ�.");
		}
		Map data = new HashMap<>();
		data.put("email", toAddress);
		data.put("password", securityService.encryptPassword("abcd1234"));

		memberDAO.modifyMember(data);

		SimpleMailMessage message = new SimpleMailMessage();

		String subject = "�ȳ��ϼ��� ��ī�������ܰ��Դϴ�." + name;
		subject += "�� �ӽ� ��й�ȣ�Դϴ�.";
		String text = "�ȳ��ϼ��� ��ī�������ܰ��Դϴ�.\n";
		text += name + "���� �ӽ� ��й�ȣ�� abcd1234 �Դϴ�.";

		message.setFrom("iiedby2500@naver.com");
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

		Map result = new HashMap<>();
		result.put("success", "������ �̸��Ϸ� �ӽú�й�ȣ�� ���۵Ǿ����ϴ�.");

		return result;
	}
}
