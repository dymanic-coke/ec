package com.spring.ec.seller.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.ec.seller.vo.BookingVO;

public interface BookingDAO {
	public List<BookingVO> selectAllBookingList(String seller_id) throws DataAccessException;
}
