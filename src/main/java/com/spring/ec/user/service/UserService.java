package com.spring.ec.user.service;

import java.util.List;

import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.MemberVO;

public interface UserService {
	
	//사용자 게시판 연결 메소드
	public List<BoardVO> listBoards()throws Exception;
	
	public List<BoardVO> eatListBoards()throws Exception;
	
	public List<BoardVO> seeListBoards()throws Exception;
	
	public BoardVO viewBoard(int list_num)throws Exception;
}
