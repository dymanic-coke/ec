package com.spring.ec.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ec.user.dao.UserDAO;
import com.spring.ec.user.vo.BoardVO;
import com.spring.ec.user.vo.CommentVO;
import com.spring.ec.user.vo.ImageVO;
import com.spring.ec.user.vo.StoreVO;

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
		userDAO.insertNewImage(boardMap);
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
	
	@Override
	public List<StoreVO> selectAllStores()throws Exception{
		return userDAO.selectAllStoresList();
	}
}
