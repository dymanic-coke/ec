package com.spring.ec.seller.service;

import java.util.List;

import com.spring.ec.seller.vo.BookingVO;

public interface BookingService {
	public List<BookingVO> bookingAllList(String seller_id) throws Exception;
}
