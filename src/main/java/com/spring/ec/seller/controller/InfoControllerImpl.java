package com.spring.ec.seller.controller;

import java.io.File;
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

import com.spring.ec.seller.service.InfoService;
import com.spring.ec.seller.vo.ProductVO;
import com.spring.ec.seller.vo.StoreinfosumVO;


@Controller("infoController")
public class InfoControllerImpl implements InfoController  {
	private static final String MENU_IMAGE_REPO="C:\\EATSEE\\menu_image";
	private static final Logger logger = LoggerFactory.getLogger(InfoControllerImpl.class);
	@Autowired
	private InfoService infoService;
	
	
	// �뿉�윭�럹�씠吏� �씠�룞
	@Override
	@RequestMapping(value = "/error.do", method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav; 
	}
	
	
	// 硫붾돱愿�由�
	@Override
	@RequestMapping(value = "/menumanage.do", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		
		//session�뿉 �궓�븘�엳�뒗 sellerid瑜� 媛��졇���빞�븿
		//MemberVO mm = (MemberVO) session.getAttribute("member");
		//String seller_id = mm.getSeller_id());
		
		String seller_id = "stest001";
		
		//�빐�떦 媛�寃뚯쓽 李�,由щ럭,�삁�빟 議고쉶
		StoreinfosumVO infosum = infoService.selectinfosum(seller_id);
		
		//�빐�떦 媛�寃� 硫붾돱 由ъ뒪�듃
		List<ProductVO> MenuList = infoService.selectMenu(seller_id);
		
		mav.addObject("infosum", infosum);
		mav.addObject("menuList", MenuList);
		mav.setViewName(viewName);
		return mav; 
	}
	
	
	
	// 硫붾돱愿�由� - 寃��깋

	@Override
	@ResponseBody 
	@RequestMapping(value = "/searchmenu.do", method = RequestMethod.GET)
	public ModelAndView searchmenu(@RequestParam("search") String search,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		Map<String, String> listMap = new HashMap<String, String>();
		//session�뿉 �궓�븘�엳�뒗 sellerid瑜� 媛��졇���빞�븿
		//MemberVO mm = (MemberVO) session.getAttribute("member");
		//String seller_id = mm.getSeller_id());
		
		String seller_id = "stest001";
		listMap.put("seller_id", seller_id);
		listMap.put("search", search);
		
		List<ProductVO> MenuList = infoService.selectsearchMenu(listMap);
		
		mav.addObject("menuList", MenuList);
		mav.setViewName(viewName);
		return mav; 
	}
	
	// 硫붾돱愿�由� - 由ъ뒪�듃 �궘�젣
	@Override
	@ResponseBody 
	@RequestMapping(value = "/deletemenu.do", method = RequestMethod.GET)
	public ModelAndView deletemenu(@RequestParam("menucheck") String params,  RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> listMap = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		//session�뿉 �궓�븘�엳�뒗 sellerid瑜� 媛��졇���빞�븿
		//MemberVO mm = (MemberVO) session.getAttribute("member");
		//String seller_id = mm.getSeller_id());
		String[] memuList = params.split(",");
		
		String seller_id = "stest001";
		listMap.put("seller_id", seller_id);
		listMap.put("params", memuList);
		
		int result = infoService.deletemenu(listMap);
		if(result >= 1) { 
			rAttr.addFlashAttribute("result", "deletesuccess");
			mav.setViewName("redirect:/menumanage.do"); 
		} else { 
			rAttr.addFlashAttribute("result", "deletefail");
			mav.setViewName("redirect:/error.do");
		}

		return mav; 
	}
	
	
	// 硫붾돱愿�由� - �긽�꽭蹂닿린 �궘�젣
	@Override
	@ResponseBody 
	@RequestMapping(value = "/delonemenu.do", method = RequestMethod.POST)
	public ModelAndView delonemenu(@RequestParam("seller_id") String seller_id, @RequestParam("pro_num") String pro_num, RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> listMap = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();

		listMap.put("seller_id", seller_id);
		listMap.put("pro_num", pro_num);
		
		int result = infoService.delonemenu(listMap);
		if(result >= 1) { 
			rAttr.addFlashAttribute("result", "deletesuccess");
			mav.setViewName("redirect:/menumanage.do"); 
		} else { 
			rAttr.addFlashAttribute("result", "deletefail");
			mav.setViewName("redirect:/error.do");
		}

		return mav; 
	}
	
	
	// 硫붾돱愿�由� - �긽�꽭�럹�씠吏�
	@Override
	@ResponseBody
	@RequestMapping(value = "/menudetail.do", method = RequestMethod.POST)
	public ProductVO menudetail(@RequestParam("pro_num") String pro_num,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String viewName = (String) request.getAttribute("viewName");
		Map<String, String> listMap = new HashMap<String, String>();
		ModelAndView mav = new ModelAndView();
		//session�뿉 �궓�븘�엳�뒗 sellerid瑜� 媛��졇���빞�븿
		//MemberVO mm = (MemberVO) session.getAttribute("member");
		//String seller_id = mm.getSeller_id());
		System.out.println(pro_num);
		String seller_id = "stest001";
		listMap.put("seller_id", seller_id);
		listMap.put("pro_num", pro_num);
		
		ProductVO menudetail = infoService.menudetail(listMap);
		System.out.println(menudetail.getPro_num());
		//mav.addObject("menudetail", menudetail);
		//mav.setViewName(viewName);

		return menudetail; 
	}
	
	// 硫붾돱愿�由� - �닔�젙
	@Override
	@RequestMapping(value = "/menumod.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity menumod(@ModelAttribute("menu") ProductVO menu,MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> menuMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			menuMap.put(name, value);
		}
		

		String imageFileName = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			infoService.modProduct(menuMap);
			if(imageFileName != null && imageFileName.length() != 0) {
				File srcFile = new File(MENU_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
				File destDir = new File(MENU_IMAGE_REPO + "\\" + "menu" + "\\" + menu.getSeller_id());
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				//String originalFileName = (String) menuMap.get("originalFileName");
				//File oldFile = new File(MENU_IMAGE_REPO + "\\" +  "menu" + "\\" + menu.getSeller_id() + "\\" + originalFileName);
				//oldFile.delete();
			}
			
			message = "<script>";
			message += " alert('湲��쓣 �닔�젙�뻽�뒿�땲�떎.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/menudetail?pro_num=" + menu.getPro_num() + "'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(MENU_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete();

			message = "<script>";
			message += " alert('�삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. �떎�떆 �떆�룄�빐 二쇱꽭�슂');";
			message += " location.href='" + multipartRequest.getContextPath() + "/error.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	
	//�씠誘몄� �븳媛� �닔�젙
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String imageFileName = null;
		Map<String, String> menuMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			File file = new File(MENU_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(MENU_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName));
				}
			}
		}
		return imageFileName;
	}
	
	
	
	
	

}