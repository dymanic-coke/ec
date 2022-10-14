package com.spring.ec.seller.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.ec.seller.vo.BookingVO;


@Repository("sellerBookingDAO")
public class BookingDAOImpl implements BookingDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BookingVO> selectAllBookingList(String seller_id) throws DataAccessException {
		List<BookingVO> bookingList = sqlSession.selectList("mapper.booking.selectAllBookingList", seller_id);
		return bookingList;
	}
}
