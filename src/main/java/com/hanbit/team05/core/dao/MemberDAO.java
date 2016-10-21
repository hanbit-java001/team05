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


		int result = sqlSession.insert("member.insertJoinMember", memberVO);

		if (result != 1) {
			throw new RuntimeException("회원가입에러");
		}

		return sqlSession.insert("member.insertJoinMember", memberVO);
	}
}
