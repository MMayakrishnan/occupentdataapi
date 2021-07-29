package com.hotel.occupentdataapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.occupentdataapi.pojo.BookingDetails;

@Service
public interface OccupentService {
	
	public List<BookingDetails> getOccupantDetails(Date statsDate);
	
	public List<BookingDetails> getAllOccupantsDetail(int pageNo,int pageSize);
}
