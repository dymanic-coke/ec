package com.spring.ec.seller.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.ec.seller.vo.ReviewAnsVO;

@Repository("reviewAnsDAO")
public class ReviewAnsDAOImpl implements ReviewAnsDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReviewAnsVO> selectAllReAnsList(Map reAns) throws DataAccessException {
		int page = (Integer) reAns.get("page");
		page = (page - 1) * 10;
		reAns.put("page", page);
		String period = (String) reAns.get("period");
		List<ReviewAnsVO> reAnsList = null;
		if(period == null || period.equals("all") ) {
			reAnsList = sqlSession.selectList("mapper.review.selectAllReAnsList", reAns);			
		} else if(period.equals("today")) {
			reAnsList = sqlSession.selectList("mapper.review.selectTodayReAnsList", reAns);
		} else if(period.equals("yesterday")) {
			reAnsList = sqlSession.selectList("mapper.review.selectYesterReAnsList", reAns);
		}		
		return reAnsList;
	}
	
	@Override
	public int selectReAnsListCount(Map reAns) throws DataAccessException {
		String period = (String) reAns.get("period");
		int reAnsCount = 0;
		if(period == null || period.equals("all") ) {
			reAnsCount = sqlSession.selectOne("mapper.review.selectReAnsListCount", reAns);			
		} else if(period.equals("today")) {
			reAnsCount = sqlSession.selectOne("mapper.review.selectTodayReAnsListCount", reAns);
		} else if(period.equals("yesterday")) {
			reAnsCount = sqlSession.selectOne("mapper.review.selectYesterReAnsListCount", reAns);
		}		
		return reAnsCount;
	}
}
