package com.hanbit.team05.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.team05.core.dao.ConsultDAO;
import com.hanbit.team05.core.vo.ConsultVO;

@Service
public class ConsultService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultService.class);

	@Autowired
	private ConsultDAO consultDAO;

	@Transactional
	public int addConsult(ConsultVO consult) {

		int result = consultDAO.addConsult(consult);

		if (result != 1) {
			throw new RuntimeException("상담접수 도중 에러가 발생하였습니다. 다시시도 해주시길 바랍니다.");
		}

		return result;
	}

}
