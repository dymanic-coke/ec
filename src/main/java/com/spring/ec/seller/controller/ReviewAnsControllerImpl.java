package com.spring.ec.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ec.seller.service.ReviewAnsService;
import com.spring.ec.seller.vo.ReviewAnsVO;
import com.spring.ec.user.vo.ReviewVO;

@Controller("reviewAnsController")
public class ReviewAnsControllerImpl implements ReviewAnsController {
	private static final Logger logger = LoggerFactory.getLogger(MainControllerImpl.class);
	
	@Autowired
	private ReviewAnsService reAnsService;
	@Autowired
	private ReviewVO reviewVO;
	@Autowired
	private ReviewAnsVO reAnsVO;
	
	@Override
	@RequestMapping(value = "/seller/reviewManage", method = RequestMethod.GET)
	public ModelAndView reviewManageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
//		SellerVO sellerVO = (SellerVO) session.getAttribute("sellerMember");
//		String seller_id = sellerVO.getSeller_id();
		String seller_id = "stest002";
		Map reAns = new HashMap();
		reAns.put("seller_id", seller_id);
		String period = request.getParameter("period");
		reAns.put("period", period);
		int boardCount = reAnsService.reAnsListCount(reAns);
		int displayNum = 10;
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		int endPage = (int) (Math.ceil(page / (double) displayNum) * displayNum);
		int tempEndPage = (int) (Math.ceil(boardCount / (double) displayNum));
		int startPage = (endPage - displayNum) + 1;
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		boolean prev = startPage == 1 ? false : true;
		boolean next = endPage * displayNum >= boardCount ? false : true;

		Map paging = new HashMap();
		paging.put("boardCount", boardCount);
		paging.put("displayNum", displayNum);
		paging.put("startPage", startPage);
		paging.put("nowPage", page);
		paging.put("endPage", endPage);
		paging.put("prev", prev);
		paging.put("next", next);
		
		reAns.put("page", page);
		List reAnsList = reAnsService.reAnsAllList(reAns);
		ModelAndView mav = new ModelAndView();
		mav.addObject("reAnsList", reAnsList);
		mav.addObject("paging", paging);
		mav.setViewName(viewName);
		return mav; 
	}
}
