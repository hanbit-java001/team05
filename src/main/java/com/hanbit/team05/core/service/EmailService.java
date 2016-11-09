package com.hanbit.team05.core.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.ConsultDAO;
import com.hanbit.team05.core.dao.MemberDAO;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
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
		Session session = SessionHelper.getSession();

		if (member == null || !member.getName().equals(name)) {
			throw new RuntimeException("�������� �ʴ� ȸ���Դϴ�.");
		}

		String[] stringArray = {"a","b","c","d","e","f","g","h","i","j","k","l","n","m","o","p","q","r","s","t","u","v","w","x","y","z"};

		int randomNumber1 = new Random().nextInt(10000)+100;
		int randomNumber2 =	new Random().nextInt(30000)+100;
		int totalRandom = (randomNumber1 + randomNumber2) ^+ 100;

		String temporarilyPassword = "";

		for (int i = 0; i < 4; i++) {
			int randomNumber = new Random().nextInt(stringArray.length)+0;
			temporarilyPassword += stringArray[randomNumber];
		}

		temporarilyPassword += String.valueOf(totalRandom);

		Map data = new HashMap<>();
		data.put("email", toAddress);
		data.put("password", securityService.encryptPassword(temporarilyPassword));

		memberDAO.modifyMember(data);

		SimpleMailMessage message = new SimpleMailMessage();

		String subject = "�ȳ��ϼ��� ��ī�������ܰ��Դϴ�." + name;
		subject += "�� �ӽ� ��й�ȣ�Դϴ�.";
		String text = "�ȳ��ϼ��� ��ī�������ܰ��Դϴ�.\n";
		text += name + "���� �ӽ� ��й�ȣ��" + temporarilyPassword + "�Դϴ�.";

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
