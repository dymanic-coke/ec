package com.spring.ec.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.ec.seller.vo.ProductVO;
import com.spring.ec.seller.vo.SellerVO;
import com.spring.ec.seller.vo.StoreVO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.ImageVO;
import com.spring.ec.user.vo.MemberVO;
import com.spring.ec.user.vo.ReservVO;
import com.spring.ec.user.vo.ReviewVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//먹플리 볼플리
	@Override
	public List selectAllBoardsList() throws DataAccessException{
		List<BoardVO> boardsList = sqlSession.selectList("mapper.board.selectAllBoardsList");
		return boardsList;
	}
	
	@Override
	public List selectEatBoardsList() throws DataAccessException{
		List<BoardVO> boardsList = sqlSession.selectList("mapper.board.selectEatBoardsList");
		return boardsList;
	}
	
	@Override
	public List selectSeeBoardsList() throws DataAccessException{
		List<BoardVO> boardsList = sqlSession.selectList("mapper.board.selectSeeBoardsList");
		return boardsList;
	}
	
	@Override
	public BoardVO selectBoard(int list_num) throws DataAccessException{
		return sqlSession.selectOne("mapper.board.selectBoard", list_num);
		
	}
	//이미지 파일 리스트 호출
	@Override
	public List selectImageFileList(int list_num) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList", list_num);
		return imageFileList;
	}
	
	@Override
	public void addHits(int list_num)throws DataAccessException{
		sqlSession.update("mapper.board.addHits", list_num);
	}
	
	@Override
	public int insertNewBoard(Map boardMap) throws Exception {
		int list_num = selectNewList_num();
		boardMap.put("list_num", list_num);
		sqlSession.insert("mapper.board.insertNewBoard", boardMap);
		return list_num;
	}
	
	@Override
	public void insertNewImage(Map boardMap) throws DataAccessException {
		List<ImageVO> image_fileList = (ArrayList)boardMap.get("image_fileList");
		int list_num = (Integer)boardMap.get("list_num");
		int image_num = selectNewImage_num();
		for(ImageVO imageVO : image_fileList) {
			imageVO.setImage_num(++image_num);
			imageVO.setList_num(list_num);
		}
		sqlSession.insert("mapper.board.insertNewImage", image_fileList);
	}
	
	@Override
	public List selectAllCommentsList(int list_num) throws DataAccessException{
		List<BoardVO> commentsList = sqlSession.selectList("mapper.board.selectAllCommentsList", list_num);
		return commentsList;
	}
	
	@Override
	public int insertNewComment(Map commentMap) throws Exception {
		int comment_num = selectNewComment_num();
		commentMap.put("comment_num", comment_num);
		sqlSession.insert("mapper.board.insertNewComment", commentMap);
		return comment_num;
	}
	
	private int selectNewList_num() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewList_num");
	}
	
	
	private int selectNewImage_num() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImage_num");
	}
	
	private int selectNewComment_num() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewComment_num");
	}
	
	// 카테고리
	@Override
	public List<SellerVO> selectAllStores() throws Exception {
		List<SellerVO> articlesList = sqlSession.selectList("mapper.member.selectAllStores");
		return articlesList;
	}
	
	/*키워드 검색*/
	@Override
	public List<SellerVO> selectSearchStores(Map<String, String> listMap) throws Exception {
		List<SellerVO> articlesList = sqlSession.selectList("mapper.member.selectSearchStores",listMap);
		return articlesList;
	}
	
	/*지역 검색*/
	@Override
	public List<SellerVO> selectsearcharea(String area) throws Exception {
		List<SellerVO> articlesList = sqlSession.selectList("mapper.member.selectsearcharea",area);
		return articlesList;
	}
	
	/*가게 상세 검색*/
	@Override
	public StoreVO selectstoreInfo(String seller_id) throws Exception {
		StoreVO articlesList = sqlSession.selectOne("mapper.member.selectstoreInfo",seller_id);
		return articlesList;
	}
	
	/*메뉴검색*/
	@Override
	public List<ProductVO> selectMenu() throws Exception {
		List<ProductVO> articlesList = sqlSession.selectList("mapper.member.selectMenu");
		return articlesList;
	}
	
	/*리뷰*/
	@Override
	public List<ReviewVO> selectReview() throws Exception {
		List<ReviewVO> articlesList = sqlSession.selectList("mapper.member.selectReview");
		return articlesList;
	}
	
	/*리뷰 좋아요 up*/
	@Override
	public int updatereviewlike(int review_num) throws Exception {
		int result = sqlSession.update("mapper.member.updatereviewlike", review_num);
		return result;
	}
	
	
	/*리뷰 좋아요 select*/
	@Override
	public String selectreviewlike(int review_num) throws Exception {
		String result = sqlSession.selectOne("mapper.member.selectreviewlike", review_num);
		return result;
	}
	// 예약
	
	@Override 
	public ReservVO selectStoreInfo2(String seller_id) throws DataAccessException{ 
		return sqlSession.selectOne("mapper.reserv.selectStoreInfo", seller_id); 
	}
	//로그인
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}
	//회원가입
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}
}
