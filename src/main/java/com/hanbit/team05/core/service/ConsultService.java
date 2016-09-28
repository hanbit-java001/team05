package com.hanbit.team05.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.ConsultDAO;

@Service
public class ConsultService {

	private static final Logger logger = LoggerFactory.getLogger(ConsultService.class);

	@Autowired
	private ConsultDAO consultDAO;

}
