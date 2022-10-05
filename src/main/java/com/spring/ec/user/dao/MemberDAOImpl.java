package com.spring.ec.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.ec.user.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	// 로그인
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}

	// 회원가입
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	// 비밀번호 찾기
	@Override
	public String find_pwd_Result(MemberVO memberVO) throws DataAccessException {
		String vo = sqlSession.selectOne("mapper.member.find_pwd_Result", memberVO);
		return vo;
	}

	// 아이디 찾기
	@Override
	public String find_id_Result(MemberVO memberVO) throws DataAccessException {
		String vo = sqlSession.selectOne("mapper.member.find_id_Result", memberVO);
		return vo;
	}

}
