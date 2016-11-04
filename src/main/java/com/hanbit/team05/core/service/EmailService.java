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
				throw new RuntimeException("다시 시도해주세요.");
			}

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("iiedby2500@naver.com");
			message.setTo(toAddress);
			message.setSubject("안녕하세요 스카이정형외과입니다.");
			message.setText(replyMessage);
			mailSender.send(message);

			Map result = new HashMap<>();
			result.put("success", "이메일을 정상적으로 보내는데 성공하였습니다.");

			return result;

		} catch (Exception e) {
			throw new RuntimeException("이메일를 보내는데 에러가 발생하였습니다. 다시 시도해주세요.");
		}
	}

	public Map findEmail(String toAddress, String name) {


		MemberVO member = memberDAO.selectMember(toAddress);

		if (member == null) {
			throw new RuntimeException("존재하지 않는 회원입니다.");
		}
		Map data = new HashMap<>();
		data.put("email", toAddress);
		data.put("password", securityService.encryptPassword("abcd1234"));

		memberDAO.modifyMember(data);

		SimpleMailMessage message = new SimpleMailMessage();

		String subject = "안녕하세요 스카이정형외과입니다." + name;
		subject += "님 임시 비밀번호입니다.";
		String text = "안녕하세요 스카이정형외과입니다.\n";
		text += name + "님의 임시 비밀번호는 abcd1234 입니다.";

		message.setFrom("iiedby2500@naver.com");
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

		Map result = new HashMap<>();
		result.put("success", "쓰고계신 이메일로 임시비밀번호가 전송되었습니다.");

		return result;
	}
}
