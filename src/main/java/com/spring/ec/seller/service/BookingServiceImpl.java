package com.spring.ec.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ec.seller.dao.BookingDAO;
import com.spring.ec.seller.vo.BookingVO;

@Service("sellerBookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingDAO bookingDAO;
	
	@Override
	public List<BookingVO> bookingAllList(String seller_id) throws Exception{
		return bookingDAO.selectAllBookingList(seller_id);
	}
}
