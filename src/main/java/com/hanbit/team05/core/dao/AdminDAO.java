package com.hanbit.team05.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.team05.core.vo.ConsultVO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<ConsultVO> getConsult(Map data) {
		return sqlSession.selectList("admin.selectConsultList", data);
	}

	public int totalCount(String flag){

		return sqlSession.selectOne("admin.selectConsultCount", flag);
	}
}
