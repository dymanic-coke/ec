package com.spring.ec.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.ec.user.vo.BoardVO;

public interface UserDAO {
	public List selectAllBoardsList() throws DataAccessException;
	
	public List selectEatBoardsList() throws DataAccessException;
	
	public List selectSeeBoardsList() throws DataAccessException;
	
	public BoardVO selectBoard(int list_num) throws DataAccessException;
	
	public List selectImageFileList(int list_num) throws DataAccessException;
	
	public void addHits(int list_num)throws DataAccessException;
	
	public int insertNewBoard(Map boardMap) throws Exception;
	
	public void insertNewImage(Map boardMap) throws DataAccessException;
	
	public List selectAllCommentsList(int list_num) throws DataAccessException;
	
	public int insertNewComment(Map commentMap) throws Exception;
	
	public List selectAllStoresList() throws DataAccessException;
}
