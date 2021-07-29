package com.hotel.occupentdataapi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hotel.occupentdataapi.exception.ResourceNotAvailableException;
import com.hotel.occupentdataapi.pojo.BookingDetails;
import com.hotel.occupentdataapi.repository.BookingDetailsRepository;
import com.hotel.occupentdataapi.service.OccupentService;

@Service
public class OccupentServiceImpl implements OccupentService{
	
	@Autowired
	BookingDetailsRepository bookingDetailsRepository;
	
	private static Logger logger=LoggerFactory.getLogger(OccupentServiceImpl.class);
	
	
	// queries booking table and get reservation details and occupant details on particular date, throws custom exception if no room was booked on the day
	public List<BookingDetails> getOccupantDetails(Date statsDate) {
		logger.info("Occupant details requested for date"+statsDate);
		List<BookingDetails> reservations=bookingDetailsRepository.getReservations(new SimpleDateFormat("yyyy-MM-dd").format(statsDate));
		if(!reservations.isEmpty()) {
			logger.info("Occupant details generated for date"+statsDate);
			return reservations;
		}
		else {
			logger.info("No room was booked for "+statsDate);
			throw new ResourceNotAvailableException("No Room was booked on specified date ");
		}
			
	}


	// queries booking table and get all occupant details , throws custom exception if no detail available for requested page
	public List<BookingDetails> getAllOccupantsDetail(int pageNo,int pageSize) {
		 List<BookingDetails> reservations=bookingDetailsRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
		 if(!reservations.isEmpty()) {
			 logger.info("Occupent details generated for page :"+pageNo+" with size :"+pageSize);
				return reservations;
		 }
			else {
				 logger.info("No Occupant Detail available for page :"+pageNo+" with size :"+pageSize);
				 throw new ResourceNotAvailableException("No Occupant Detail available on the desired page ");
			}
				
	}

}
