package com.spring.ec.user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.service.UserService;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.ImageVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.ReservVO;

@Controller("userController")
public class UserControllerImpl implements UserController  {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
	private static final String U_IMAGE_REPO="C:\\board\\u_board_imagefile";
	@Autowired
	private UserService userService;
	@Autowired
	MemberVO memberVO;
	@Autowired
	BoardVO boardVO;
	@Autowired
	CommentVO commentVO;
	@Autowired
	ReservVO reservVO;
	
	//메인 페이지
	@Override
	@RequestMapping(value = "/main.do" , method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	// 멕플리 볼플리 페이지
	@Override
	@RequestMapping(value = "/user/u_board/eatpl", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listEatBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.eatListBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/u_board/seepl", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listSeeBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.seeListBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/u_board/u_boardView", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewboard(@RequestParam("list_num") int list_num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		userService.addHits(list_num);
		Map boardMap = userService.viewBoard(list_num);
		List commentsList = userService.listComments(list_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		mav.addObject("comments", commentsList);
		return mav;
	}

	@Override
	@RequestMapping(value = "/user/u_board/boardForm", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView boardform(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/addNewboard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
	  multipartRequest.setCharacterEncoding("utf-8");
	  String imageFileName = null;
	  
	  Map boardMap = new HashMap();
	  Enumeration enu = multipartRequest.getParameterNames();
	  while(enu.hasMoreElements()) { 
	  String name = (String)enu.nextElement();
	  String value = multipartRequest.getParameter(name);
	  boardMap.put(name, value); 
	  }
	  
	   
	  HttpSession session = multipartRequest.getSession();
	  MemberVO memberVO = (MemberVO)session.getAttribute("member");
//	  String user_id = memberVO.getUser_id();
	  boardMap.put("user_id", "test1");
	  boardMap.put("parent_num", 0);
	  
	  List<String> fileList = upload(multipartRequest);
	  List<ImageVO> image_fileList = new ArrayList<ImageVO>();
	  if(fileList != null && fileList.size() !=0) {
		  for(String fileName : fileList){ 
			  ImageVO imageVO = new ImageVO();
			  imageVO.setImage_fileName(fileName);
			  image_fileList.add(imageVO); 
		  }
		  boardMap.put("image_fileList",image_fileList);
	  }
	  String message;
	  ResponseEntity resEnt = null;
	  HttpHeaders responseHeaders = new HttpHeaders();
	  responseHeaders.add("Content-Type","text/html; charset=utf-8");
	  try { 
		  int list_num = userService.addNewBoard(boardMap);
		  if(image_fileList != null && image_fileList.size() != 0) {
			  for(ImageVO imageVO:image_fileList) {
				  imageFileName = imageVO.getImage_fileName();
				  File srcFile = new File(U_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName); 
				  File destDir = new File(U_IMAGE_REPO + "\\" + list_num);
				  //destDir.mkdirs(); 
				  FileUtils.moveFileToDirectory(srcFile, destDir, true);
				  } 
			  }
		  
		  message = "<script>"; 
		  message += " alert('글작성에 성공하셨습니다.');";
		  message += " location.href='" + multipartRequest.getContextPath() + "/user/u_board'; ";
		  message += " </script>";
		  resEnt = new ResponseEntity(message, responseHeaders,HttpStatus.CREATED); 
		  } catch(Exception e) { 
			  if(image_fileList != null && image_fileList.size() != 0) { 
				  for(ImageVO imageVO:image_fileList) {
					  	imageFileName = imageVO.getImage_fileName(); 
					  	File srcFile = new File(U_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					  	srcFile.delete(); 
				  } 
			}
	  
		 message = "<script>"; 
		 message += " alert('글작성에 실패 하였습니다.');"; 
		 message += " location.href='" + multipartRequest.getContextPath() + "/user/u_board/boardForm'; "; 
		 message += " </script>"; 
		 resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		 e.printStackTrace(); 
		 } 
	  	 return resEnt; 
	  }
	
	@Override
	@RequestMapping(value = "/user/u_board/addcomment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addComment(HttpServletRequest Request, HttpServletResponse response)
			throws Exception {
		Request.setCharacterEncoding("utf-8");
		Map<String, Object> commentMap = new HashMap<String, Object>();
		Enumeration enu = Request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = Request.getParameter(name);
			commentMap.put(name, value);
		}
		HttpSession session = Request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String comment_id = memberVO.getUser_id();
		int parent_num = Integer.parseInt(Request.getParameter("parent_num"));
		commentMap.put("parent_num", parent_num);
		commentMap.put("comment_id", comment_id);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int comment_num = userService.addNewComment(commentMap);
			message = "<script>";
			message += " location.href='" + Request.getContextPath() + "/user/u_board/u_boardView'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + Request.getContextPath() + "/user/u_board/u_boardView'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	private List<String> upload(MultipartHttpServletRequest multipartRequest)throws Exception
	{
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(U_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);

			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(U_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName));
				}
			}
		}
		return fileList;
	}
	//카테고리
	@Override
	@RequestMapping(value = "/category.do", method = RequestMethod.GET)
	public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/category/categorymain";
		System.out.println("viewName::" + viewName);
		List StoreList = userService.selectAllStores();
		List MenuList = userService.selectMenu();
		List ReviewList = userService.selectReview();

		
		System.out.println("StoreList::" + StoreList);
		System.out.println("MenuList::" + MenuList);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		mav.addObject("menuList", MenuList);
		mav.addObject("reviewList", ReviewList);
		return mav;
	}

	/*검색*/
	@Override
	@RequestMapping(value = "/searchcategory.do", method = RequestMethod.GET)
	public ModelAndView searchcategory(@RequestParam(value = "search") String search,@RequestParam(value = "area") String area, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = "/category/searchcategory";
		System.out.println("viewName::" + viewName);
		List<String> searchword = Arrays.asList(search.split(" "));
		Map<String, String> listMap = new HashMap<String, String>();
		for (int i = 0; i < searchword.size(); i++) {
			listMap.put("key0" + (i + 1), searchword.get(i));

		}
		System.out.println("listMap::::" + listMap);
		System.out.println("Type is: " + listMap.getClass());
		
		if(area == null || area.equals("null") ) {
			
		} else {
			listMap.put("area", area);
		}
		
		
		List StoreList = userService.selectSearchStores(listMap);
		System.out.println("StoreList::" + StoreList);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		return mav;
	}
	
	
	/*상세조회*/
	@Override
	@RequestMapping(value = "/storeInfo.do", method = RequestMethod.GET)
	public ModelAndView storeInfo(@RequestParam(value = "seller_id") String seller_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = "/category/storeInfo";
		System.out.println("viewName::" + viewName);
		//Map<String, String> listMap = new HashMap<String, String>();

		StoreVO StoreList = userService.selectstoreInfo(seller_id);
		System.out.println("StoreList::" + StoreList);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("StoreList", StoreList);
		return mav;
	}
	
	
	/* 리뷰 좋아요 up*/
	@Override
	@RequestMapping(value = "/reviewlike.do", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String reviewlike( HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int reviewnum = 1;
		userService.updatereviewlike(reviewnum);
		String result = userService.selectreviewlike(reviewnum);
		System.out.println("result::" + result);
		return result;
	}
	
	//예약페이지
	@Override
	@RequestMapping(value = "/reservation.do" , method = {RequestMethod.POST, RequestMethod.GET})
	   public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String seller_id = "stest002";
		ReservVO reservInfo = userService.selectStoreInfo(seller_id);
		ModelAndView mav = new ModelAndView();		
		mav.setViewName(viewName);
		mav.addObject("reservInfo", reservInfo);
		return mav;
	   }
	@Override
	@RequestMapping(value = "/reservCheck" , method = {RequestMethod.POST, RequestMethod.GET})
	   public ModelAndView reservCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	   }
	
	//로그인
	@RequestMapping(value = "/user/loginForm.do", method = RequestMethod.GET)
	public ModelAndView loginform(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	@RequestMapping(value = "/user/memberForm_main.do", method = RequestMethod.GET)
	public ModelAndView memberform_main(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		System.out.println("controller:" + viewName);
		return mav;
		
	}
	@RequestMapping(value = "/user/memberForm_udetail.do", method = RequestMethod.GET)
	public ModelAndView memberform_udetail(@RequestParam(value = "result", required = false) String result,
			/*가져올데이터이름(value="result") 가져올데이터를담을변수(String result) */	
		@RequestParam(value = "action", required = false) String action, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//String viewName = getViewName(request); interceptor이용하기에 아래 getviewName주석처리
		String viewName = (String)request.getAttribute("viewName"); /*intercepotor getviewName메서드이용*/
		HttpSession session = request.getSession();  /*session객체 이용하여 viewName받음 */
		session.setAttribute("action", action); /*session객체 이용하여 action키 값 받음 */
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result); /*ModelAndView객체 이용하여 값 추가*/
		mav.setViewName(viewName);
		System.out.println("controller:" + viewName);
		return mav;	 /* ModelAndView객체 이용하여 값을 전달 */
	}
	@Override
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, 
			RedirectAttributes rAttr, 
			HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		ModelAndView mav= new ModelAndView();
		memberVO = userService.login(member);
		if(memberVO != null) { /*DB에 있는 값과 일치하지않으면 */
			HttpSession session = request.getSession();
			session.setAttribute("member",memberVO);
			session.setAttribute("isLogOn", true);
			String action =(String) session.getAttribute("action");
			session.removeAttribute("action");
			if(action!=null) {
				mav.setViewName("redirect:"+ action); /* action도메인 비어있음 */
			}else {
				mav.setViewName("redirect:/main.do");  /*로그인 성공시*/
			}
		}else {
			rAttr.addFlashAttribute("result", "loginFailed"); /*login.jsp의 loginFailed <choose>구문 실행 */
			mav.setViewName("redirect:/user/loginForm.do");   /* login.jsp redirect한다 */
		}
		return mav;
	}
	@Override
	@RequestMapping(value = "/user/u_regadmin.do" , method = RequestMethod.GET)
	public ModelAndView regadmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	@Override
	@RequestMapping(value="/user/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/user/addMember.do",method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		int result =0;
		result= userService.addMember(member);
		ModelAndView mav= new ModelAndView("redirect:/main.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/find_id.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView find_id(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*interceptor에서 gitviewname()메서드 잘라낸 도메인(viewName)을 setAttribute한걸 getAttribute로받음 */
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		System.out.println("controller : main.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/find_pwd.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView find_pwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*interceptor에서 gitviewname()메서드 잘라낸 도메인(viewName)을 setAttribute한걸 getAttribute로받음 */
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		System.out.println("controller : main.do");
		return mav;
	}
	
	//마이페이지
	
	@RequestMapping(value = "/mypage.do" , method = RequestMethod.GET)
	   public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/mypage";
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	   }
	
	@RequestMapping(value = "/uask.do" , method = RequestMethod.GET)
	public ModelAndView uask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	@RequestMapping(value = "/ulike.do" , method = RequestMethod.GET)
	public ModelAndView ulike(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/mypage/order_list.do" , method = RequestMethod.GET)
	   public ModelAndView orderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/mypage/order_list";
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	   }
	
	@RequestMapping(value = "/mypage/wish.do" , method = RequestMethod.GET)
	   public ModelAndView wish(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/mypage/wish";
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	   }
	
	@RequestMapping(value = "/mypage/recent_view.do" , method = RequestMethod.GET)
	   public ModelAndView recentView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/mypage/recent_view";
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	   }
}
