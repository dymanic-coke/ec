package com.spring.ec.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.ec.seller.vo.ProductVO;
import com.spring.ec.seller.vo.SellerVO;
import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.dao.UserDAO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.ImageVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.ReservVO;
import com.spring.ec.user.vo.ReviewVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	// 먹플리 볼플리 
	@Override
	public List<BoardVO> listBoards()throws Exception{
		return userDAO.selectAllBoardsList();
	}
	
	@Override
	public List<BoardVO> eatListBoards()throws Exception{
		return userDAO.selectEatBoardsList();
	}
	
	@Override
	public List<BoardVO> seeListBoards()throws Exception{
		return userDAO.selectSeeBoardsList();
	}
	
	@Override
	public Map viewBoard(int list_num) throws Exception{
		Map articleMap = new HashMap();
		BoardVO boardVO = userDAO.selectBoard(list_num);
		List<ImageVO> image_fileList = userDAO.selectImageFileList(list_num);
		articleMap.put("board", boardVO);
		articleMap.put("image_fileList", image_fileList);
		return articleMap;
	}
	
	@Override
	public int addNewBoard(Map boardMap) throws Exception {
		int list_num = userDAO.insertNewBoard(boardMap);
		boardMap.put("list_num", list_num);
		/* userDAO.insertNewImage(boardMap); */
		return list_num;
	}
	
	@Override
	public void addHits(int list_num)throws Exception{
		userDAO.addHits(list_num);
	}
	// 게시글 comment 메소드
	@Override
	public List<CommentVO> listComments(int list_num)throws Exception{
		return userDAO.selectAllCommentsList(list_num);
	}
	@Override
	public int addNewComment(Map commentMap) throws Exception {
		int comment_num = userDAO.insertNewComment(commentMap);
		commentMap.put("comment_num", comment_num);
		return comment_num;
	}
	
	// 카테고리 노출
	@Override
	public List<SellerVO> selectAllStores() throws Exception {
		List<SellerVO> StoreList = userDAO.selectAllStores();
		return StoreList;
	}
	
	@Override
	public List<SellerVO> selectSearchStores(Map<String, String> listMap) throws Exception {
		List<SellerVO> StoreList = userDAO.selectSearchStores(listMap);
		return StoreList;
	}
	
	@Override
	public List<SellerVO> selectsearcharea(String area) throws Exception {
		List<SellerVO> StoreList = userDAO.selectsearcharea(area);
		return StoreList;
	}
	
	@Override
	public StoreVO selectstoreInfo(String seller_id) throws Exception {
		StoreVO StoreList = userDAO.selectstoreInfo(seller_id);
		return StoreList;
	}
	
	@Override
	public List<ProductVO> selectMenu() throws Exception {
		List<ProductVO> MenuList = userDAO.selectMenu();
		return MenuList;
	}
	
	@Override
	public List<ReviewVO> selectReview() throws Exception {
		List<ReviewVO> ReviewList = userDAO.selectReview();
		return ReviewList;
	}
	
	@Override
	public int updatereviewlike(int reviewnum) throws Exception {
		return userDAO.updatereviewlike(reviewnum);
	}
	
	@Override
	public String selectreviewlike(int reviewnum) throws Exception {
		return userDAO.selectreviewlike(reviewnum);
	}
	
	@Override
	public ReservVO selectStoreInfo(String seller_id) throws Exception{
		return userDAO.selectStoreInfo2(seller_id);
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException{
		return userDAO.loginById(memberVO);
	}
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return userDAO.insertMember(member);
	}
}
