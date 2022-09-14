package com.spring.ec.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ec.user.dao.UserDAO;
import com.spring.ec.user.vo.BoardVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
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
	public BoardVO viewBoard(int list_num) throws Exception{
		BoardVO boardVO = userDAO.selectBoard(list_num);
		return boardVO;
	}
}
