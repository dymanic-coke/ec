package com.spring.ec.seller.service;

import java.util.List;
import java.util.Map;

import com.spring.ec.seller.vo.ReviewAnsVO;
import com.spring.ec.user.vo.ReviewVO;

public interface ReviewAnsService {
	
	public List<ReviewAnsVO> reAnsAllList(Map reAns) throws Exception;
	
	public int reAnsListCount(Map reAns) throws Exception;
}
