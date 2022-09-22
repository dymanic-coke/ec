package com.spring.ec.user.service;

import java.util.List;
import java.util.Map;

import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.StoreVO;

public interface UserService {
	
	//사용자 게시판 연결 메소드
	//플레이 리스트 게시판 목록
	public List<BoardVO> listBoards()throws Exception;
	//먹플리 게시물목록만 노출
	public List<BoardVO> eatListBoards()throws Exception;
	//볼플리 게시물 목록만 노출
	public List<BoardVO> seeListBoards()throws Exception;
	//게시물 상세창
	public Map viewBoard(int list_num) throws Exception;
	
	public void addHits(int list_num)throws Exception;
	
	public int addNewBoard(Map boardMap) throws Exception;
	
	public List<CommentVO> listComments(int list_num)throws Exception;
	
	public int addNewComment(Map commentMap) throws Exception;
	
	public List<StoreVO> selectAllStores()throws Exception;
}
