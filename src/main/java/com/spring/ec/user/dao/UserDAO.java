package com.spring.ec.user.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UserDAO {
	public List selectAllBoardsList() throws DataAccessException;
	
	public List selectEatBoardsList() throws DataAccessException;
	
	public List selectSeeBoardsList() throws DataAccessException;
}
