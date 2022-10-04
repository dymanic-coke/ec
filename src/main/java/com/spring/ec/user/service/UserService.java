package com.spring.ec.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.ec.seller.vo.ProductVO;
import com.spring.ec.seller.vo.SellerVO;
import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.NoticeVO;
import com.spring.ec.user.vo.ReservVO;
import com.spring.ec.user.vo.ReviewVO;
import com.spring.ec.user.vo.WishVO;

public interface UserService {
	
	//사용자 게시판 연결 메소드
	//플레이 리스트 게시판 목록
	public List<BoardVO> listBoards(int page)throws Exception;
	//먹플리 게시물목록만 노출
	public List<BoardVO> eatListBoards(int page)throws Exception;
	//볼플리 게시물 목록만 노출
	public List<BoardVO> seeListBoards(int page)throws Exception;
	//플레이리스트 페이지 갯수
	public int allListCount()throws Exception;
	//먹플리 페이지 갯수
	public int eatListCount()throws Exception;
	//볼플리 페이지 갯수
	public int seeListCount()throws Exception;	
	//게시물 상세창	
	public BoardVO viewBoard(int list_num) throws Exception;
	//게시물 클릭수
	public void addHits(int list_num)throws Exception;
	//게시글 쓰기
	public int addNewBoard(Map boardMap) throws Exception;
	// 댓글 불러오기
	public List<CommentVO> listComments(int list_num)throws Exception;
	// 댓글 작성
	public int addNewComment(Map commentMap) throws Exception;
	//카테고리 노출
public List<SellerVO> selectAllStores() throws Exception;
	
	public List<SellerVO> selectSearchStores(Map<String, String> listMap) throws Exception;
	
	public List<SellerVO> selectsearcharea(String area) throws Exception;
	
	public StoreVO selectstoreInfo(String seller_id) throws Exception;
	
	public List<ProductVO> selectMenu() throws Exception;
	public List<ReviewVO> selectReview() throws Exception;
	public int updatereviewlike(int reviewnum) throws Exception;
	public String selectreviewlike(int reviewnum) throws Exception;
	public List<ReviewVO> selectReviewavgsum() throws Exception;
	public int addwish(Map<String, String> listMap) throws DataAccessException;
	public List<WishVO> selectwish(String user_id) throws Exception;
	public int delwish(Map<String, String> listMap) throws DataAccessException;
	public List selectwishsum() throws DataAccessException ;
	// 예약
	public ReservVO selectStoreInfo(String seller_id) throws Exception;
	//회원가입
	public int addMember(MemberVO memberVO) throws DataAccessException; 
	//로그인
	public MemberVO login(MemberVO member)throws DataAccessException;
	
	//공지사항 이벤트
	public List<NoticeVO> noticeBoards(int page)throws Exception;
	public List<NoticeVO> eventBoards(int page)throws Exception;
	public int noticeCount()throws Exception;
	public int eventCount()throws Exception;
	public NoticeVO viewNotice(int list_num) throws Exception;
	public void addNoticeHits(int list_num)throws Exception;
}
