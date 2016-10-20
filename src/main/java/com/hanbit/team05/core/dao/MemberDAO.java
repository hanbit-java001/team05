package com.hanbit.team05.core.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.team05.core.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	public int joinMember(MemberVO memberVO) {

		memberVO.setMemberId(2);
		return sqlSession.insert("member.insertJoinMember", memberVO);
	}
}
