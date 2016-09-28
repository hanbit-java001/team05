package com.hanbit.team05.core.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultDAO {

	private static final Logger logger = LoggerFactory.getLogger(ConsultDAO.class);

	@Autowired
	private SqlSession sqlSession;

}
