package com.spring.ec.user.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.service.CateService;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.ReservVO;
import com.spring.ec.user.vo.ReviewVO;

@Controller("categoryController")
public class CateControllerImpl implements CateController {
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	private static final String REVIEW_IMAGE_REPO = "C:\\EATSEE\\review_imagefile";
	@Autowired
	private CateService cateService;
	@Autowired
	StoreVO storeVO;
	@Autowired
	MemberVO memberVO;
	@Autowired
	ReservVO reservVO;
	@Autowired
	ReviewVO reviewVO;
	
	@Override
	@RequestMapping(value = "/category.do", method = RequestMethod.GET)
	public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/category/categorymain";
		System.out.println("viewName::" + viewName);
		List StoreList = cateService.selectAllStores();
		List MenuList = cateService.selectMenu();
		List ReviewList = cateService.selectReview();
		List Reviewavgsum = cateService.selectReviewavgsum();
		List prosumList = cateService.selectprosum();
		List wishList = null;
		HttpSession session = request.getSession();

		if (session.getAttribute("member") != null) {
			MemberVO mm = (MemberVO) session.getAttribute("member");
			wishList = cateService.selectwish(mm.getUser_id());
		}

		List wishsum = cateService.selectwishsum();


		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		mav.addObject("menuList", MenuList);
		mav.addObject("reviewList", ReviewList);
		mav.addObject("reviewavgsum", Reviewavgsum);
		mav.addObject("wishList", wishList);
		mav.addObject("wishsum", wishsum);
		mav.addObject("prosumList", prosumList);
		return mav;
	}

	/* 검색 */
	@Override
	@RequestMapping(value = "/searchcategory.do", method = RequestMethod.GET)
	public ModelAndView searchcategory(@RequestParam(value = "search") String search,
			@RequestParam(value = "kind") String kind, @RequestParam(value = "area") String area,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/category/searchcategory";
		System.out.println("viewName::" + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		List<String> searchword = Arrays.asList(search.split(" "));
		Map<String, String> listMap = new HashMap<String, String>();
		for (int i = 0; i < searchword.size(); i++) {
			listMap.put("key0" + (i + 1), searchword.get(i));

		}
		System.out.println("listMap::::" + listMap);
		System.out.println("Type is: " + listMap.getClass());

		if (area == null || area.equals("null")) {
			mav.setViewName("redirect:/category.do");
		} else {
			listMap.put("area", area);
		}

		if (area.equals("null") && kind.equals("null") && search == null) {
			mav.setViewName("redirect:/category.do");
		}

		List StoreList = cateService.selectSearchStores(listMap);
		List MenuList = cateService.selectMenu();
		List ReviewList = cateService.selectReview();
		List Reviewavgsum = cateService.selectReviewavgsum();

		
		// ModelAndView mav = new ModelAndView("redirect:/category.do");
		// ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		mav.addObject("menuList", MenuList);
		mav.addObject("reviewList", ReviewList);
		mav.addObject("reviewavgsum", Reviewavgsum);
		return mav;
	}

	/* 상세조회 */
	@Override
	@RequestMapping(value = "/storeInfo.do", method = RequestMethod.GET)
	public ModelAndView storeInfo(@RequestParam(value = "seller_id") String seller_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = "/category/storeInfo";
		System.out.println("viewName::" + viewName);
		// Map<String, String> listMap = new HashMap<String, String>();

		StoreVO StoreList = cateService.selectstoreInfo(seller_id);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		return mav;
	}

	/* 리뷰 좋아요 up */
	@Override
	@RequestMapping(value = "/reviewlike.do", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String reviewlike(@RequestParam(value = "review_num") int reviewnum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		cateService.updatereviewlike(reviewnum);
		String result = cateService.selectreviewlike(reviewnum);
		System.out.println("result::" + result);
		return result;
	}

	
	//221005
	// 찜하기
	@Override
	@RequestMapping(value = "/addwish.do", method = RequestMethod.POST)
	public @ResponseBody String addwish(@RequestParam(value = "seller_id") String seller_id,
			@RequestParam(value = "user_id") String user_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Map<String, String> listMap = new HashMap<String, String>();
		listMap.put("seller_id", seller_id);
		listMap.put("user_id", user_id);
		int result = cateService.addwish(listMap);
		// 찜개수 가져오기
		String wishsellsum = cateService.selectsellerwishsum(seller_id);


		String state = "";
		if (result == 1) {
			state = "true";
		} else {
			state = "false";
		}
		return wishsellsum;
	}

	
	//221005
	// 찜삭제
	@Override
	@RequestMapping(value = "/delwish.do", method = RequestMethod.POST)
	public @ResponseBody String delwish(@RequestParam(value = "seller_id") String seller_id,
			@RequestParam(value = "user_id") String user_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Map<String, String> listMap = new HashMap<String, String>();
		listMap.put("seller_id", seller_id);
		listMap.put("user_id", user_id);
		int result = cateService.delwish(listMap);
		String wishsellsum = cateService.selectsellerwishsum(seller_id);
		String state = "";
		if (result == 1) {
			state = "false";
		} else {
			state = "true";
		}
		return wishsellsum;
	}

	//리뷰 등록
	@Override
	@RequestMapping(value="/addreview.do", method = RequestMethod.POST)
	public ModelAndView addreview(@ModelAttribute("review") ReviewVO review, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		MemberVO mm = (MemberVO) session.getAttribute("member");
		review.setUser_id(mm.getUser_id());
		int result = cateService.addreview(review);
		//result = cateService.addreview(review);
		ModelAndView mav = new ModelAndView("redirect:/category.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/reservation.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String seller_id = "stest002";
		ReservVO reservInfo = cateService.selectStoreInfo(seller_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("reservInfo", reservInfo);
		return mav;
	}

	@Override
	@RequestMapping(value = "/reservCheck", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView reservCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
}
