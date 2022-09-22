package com.spring.ec.user.controller;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ec.user.service.UserService;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.ImageVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.StoreVO;

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
	StoreVO storeVO;

	@Override
	@RequestMapping(value = "/main.do" , method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/u_board", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.listBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
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
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public ModelAndView loginform(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
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
	@RequestMapping(value="/board/addNewArticle.do", method = RequestMethod.POST)
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
	  
	  //濡쒓렇�씤 �떆 �꽭�뀡�뿉 ���옣�맂 �쉶�썝 �젙蹂댁뿉�꽌 湲��벖�씠 �븘�씠�뵒瑜� �뼸�뼱���꽌 Map�뿉 ���옣�빀�땲�떎. 
	  HttpSession session = multipartRequest.getSession();
	  MemberVO memberVO = (MemberVO)session.getAttribute("member");
	  String user_id = memberVO.getUser_id();
	  boardMap.put("user_id", user_id);
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
	
	@Override
	   @RequestMapping(value = "/category.do" , method = RequestMethod.GET)
	   public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/category/categorymain";
		List<StoreVO> storeList = userService.selectAllStores();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("storeList",storeList);
		return mav;
	   }
	
	@RequestMapping(value = "/mypage.do" , method = RequestMethod.GET)
	   public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "/mypage";
		ModelAndView mav = new ModelAndView(viewName);
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
	
	@RequestMapping(value = "/reservation.do" , method = RequestMethod.GET)
	   public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	   }
}
