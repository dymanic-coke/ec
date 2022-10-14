package com.spring.ec.seller.controller;

import java.util.List;

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

import com.spring.ec.seller.service.BookingService;
import com.spring.ec.seller.vo.BookingVO;
import com.spring.ec.seller.vo.SellerVO;


@Controller("sellerBookingController")
public class BookingControllerImpl implements BookingController {
	private static final Logger logger = LoggerFactory.getLogger(MainControllerImpl.class);
	@Autowired
	private BookingService bookService;
	@Autowired
	BookingVO bookingVO;
	@Autowired
	SellerVO sellerVO;
	//예약 현황 페이지 호출
	@Override
	@RequestMapping(value = "/seller/bookingStatus", method = RequestMethod.GET)
	public ModelAndView bookingStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
//		SellerVO sellerVO = (SellerVO) session.getAttribute("sellerMember");
//		String seller_id = sellerVO.getSeller_id();
		String seller_id = "stest002";
		List bookList = bookService.bookingAllList(seller_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bookList", bookList);
		mav.setViewName(viewName);
		return mav; 
	}
}
