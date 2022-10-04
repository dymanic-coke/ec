package com.spring.ec.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.ec.seller.vo.ProductVO;
import com.spring.ec.seller.vo.SellerVO;
import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.ReservVO;
import com.spring.ec.user.vo.ReviewVO;
import com.spring.ec.user.vo.WishVO;

public interface UserDAO {
	// 먹플리 볼플리
	public List selectAllBoardsList(int page) throws DataAccessException;
	
	public List selectEatBoardsList(int page) throws DataAccessException;
	
	public List selectSeeBoardsList(int page) throws DataAccessException;
	
	public int eatBoardPaging() throws DataAccessException;
	
	public int seeBoardPaging() throws DataAccessException;
	
	public int allBoardPaging() throws DataAccessException;
	
	public BoardVO selectBoard(int list_num) throws DataAccessException;
	
	public List selectImageFileList(int list_num) throws DataAccessException;
	
	public void addHits(int list_num)throws DataAccessException;
	
	public int insertNewBoard(Map boardMap) throws Exception;
	
	public void insertNewImage(Map boardMap) throws DataAccessException;
	
	public List selectAllCommentsList(int list_num) throws DataAccessException;
	
	public int insertNewComment(Map commentMap) throws Exception;
	// 카테고리 정보 
public List<SellerVO> selectAllStores() throws Exception;
	
	public List<SellerVO> selectSearchStores(Map<String, String> listMap) throws Exception;
	
	public List<SellerVO> selectsearcharea(String area) throws Exception;
	
	public StoreVO selectstoreInfo(String seller_id) throws Exception;
	
	public List<ProductVO> selectMenu() throws Exception;
	
	public List<ReviewVO> selectReview() throws Exception;
	
	public int updatereviewlike(int review_num) throws Exception;
	
	public String selectreviewlike(int review_num) throws Exception;
	
	public List<ReviewVO> selectReviewavgsum() throws Exception;
	
	public int addwish(Map<String, String> listMap) throws DataAccessException;
	
	public List<WishVO> selectwish(String user_id) throws Exception;
	
	public int delwish(Map<String, String> listMap) throws DataAccessException;
	
	public List selectwishsum() throws DataAccessException;
	//예약
	public ReservVO selectStoreInfo2(String seller_id) throws DataAccessException;
	
	//사용자 로그인
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
	//회원가입
	public int insertMember(MemberVO memberVO) throws DataAccessException;
}
