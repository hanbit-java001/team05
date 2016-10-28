package com.hanbit.team05.core.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.team05.core.service.SecurityService;
import com.hanbit.team05.core.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	public int emailCount(String email) {
		return sqlSession.selectOne("member.selectEmailCount", email);
	}

	public MemberVO selectMember(String email){
		return sqlSession.selectOne("member.selectMember", email);
	}

	public int joinMember(MemberVO memberVO) {
		return sqlSession.insert("member.insertJoinMember", memberVO);
	}
}
