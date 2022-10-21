package com.spring.ec.seller.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ec.seller.service.MemberService;
import com.spring.ec.seller.vo.SellerVO;

@Controller("sellerController")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memService;
	@Autowired
	SellerVO sellerVO;
	// 판매자 메인

	@Override
	@RequestMapping(value = "/seller/member/loginForm.do", method = RequestMethod.GET)
	public ModelAndView loginform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/seller/member/s_regadmin.do", method = RequestMethod.GET)
	public ModelAndView regadmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String viewName = getViewName(request); interceptor이용하기에 아래 getviewName주석처리
		String viewName = (String) request.getAttribute("viewName"); /* intercepotor getviewName메서드이용 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav; /* ModelAndView객체 이용하여 값을 전달 */
	}
	
	@Override
	@RequestMapping(value = "/seller/member/memberForm_sdetail.do", method = RequestMethod.GET)
	public ModelAndView memberform_sdetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}
	
	@Override
	@RequestMapping(value = "/seller/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") SellerVO sellerVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		int result = 0;
		sellerVO.setKeyword(request.getParameter("keyword"));
		result = memService.addMember(sellerVO);
		ModelAndView mav = new ModelAndView("redirect:/seller/member/loginForm.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/seller/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") SellerVO sellerVO, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		sellerVO = memService.login(sellerVO);
		if (sellerVO != null) { /* DB에 있는 값과 일치하지않으면 */
			HttpSession session = request.getSession();
			session.setAttribute("member", sellerVO);
			session.setAttribute("isLogOn", true);
			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");
			if (action != null) {
				mav.setViewName("redirect:" + action); /* action도메인 비어있음 */
			} else {
				mav.setViewName("redirect:/sellerMain"); /* 로그인 성공시 */
			}
		} else {
			rAttr.addFlashAttribute("result", "loginFailed"); /* login.jsp의 loginFailed <choose>구문 실행 */
			mav.setViewName("redirect:/seller/member/loginForm.do"); /* login.jsp redirect한다 */
		}
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/seller/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/seller/member/loginForm.do");
		return mav;
	}
	
	/*
	 * @Override
	 * 
	 * @RequestMapping(value="seller/emailCheck.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String emailCheck(HttpServletRequest request) throws
	 * Exception { //param은 request.getParameter와 동일하고 jsp에 있는 매개변수를 받기위해 사용
	 * //RequestMapping을 통해 .do를 호출하고 호출한 곳에서 a키가 있을 경우 //그 값은 자동으로 String user_id에
	 * 담기게 된다. //ajax에서 getParameter는 키값을 기준으로 받아온다. String seller_email=
	 * request.getParameter("seller_email");
	 * 
	 * JSONObject obj = new JSONObject(); int cnt =
	 * memService.emailCheck(seller_email); if(cnt == 0) { obj.put("result", "YES");
	 * }else { obj.put("result", "NO"); }
	 * 
	 * return obj.toJSONString();
	 * 
	 * }
	 */
	



}