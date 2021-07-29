package com.hotel.occupentdataapi.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.occupentdataapi.pojo.BookingDetails;
import com.hotel.occupentdataapi.service.OccupentService;
import com.hotel.occupentdataapi.service.impl.OccupentServiceImpl;

@RestController
@RequestMapping("/AdministrativeAPI")
public class OccupentController {

	
	@Autowired
	OccupentService occupentService;
	
	private static Logger logger=LoggerFactory.getLogger(OccupentController.class);
	
	
	// gets details of the occupent who stayed in hotl on a particular day along with the room details   
	@GetMapping("/getOccupantsStatistics/{statsDate}")
	public List<BookingDetails> getOccupantsRooms(@PathVariable("statsDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date statsDate) {
		logger.debug("Occupent detail request recieved in controller for date "+statsDate);
		return occupentService.getOccupantDetails(statsDate);
	}
	
	// gets all occupent details who stayed on hotel based on page number and page requested
	@GetMapping("/getAllOccupants/{pageNo}/{pageSize}")
	public List<BookingDetails> getAllOccupantsDetail(@PathVariable int pageNo,@PathVariable int pageSize) {
		logger.debug("Occupent detail request recieved in controller for page "+pageNo +" and page size "+pageSize);
		return occupentService.getAllOccupantsDetail(pageNo, pageSize);
	}
	
}
