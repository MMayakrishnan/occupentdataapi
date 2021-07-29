package com.hotel.occupentdataapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hotel.occupentdataapi.exception.ResourceNotAvailableException;
import com.hotel.occupentdataapi.pojo.BookingDetails;
import com.hotel.occupentdataapi.repository.BookingDetailsRepository;
import com.hotel.occupentdataapi.service.impl.OccupentServiceImpl;

public class OccupentServiceImplTest {

	
	 @InjectMocks
	 OccupentServiceImpl occupentServiceImpl;
	@Mock
	BookingDetailsRepository bookingDetailsRepository;
	

    @BeforeEach
    public  void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOccupantDetailsHappyPathTest() {
    	
    	List<BookingDetails> reservations=new ArrayList();
    	reservations.add(new BookingDetails());
    	when(bookingDetailsRepository.getReservations(Mockito.any())).thenReturn(reservations);
    	assertEquals(occupentServiceImpl.getOccupantDetails(new Date()), 1);
    }
    
    @Test
    public void getOccupantDetailsExceptionPathTest() {
    	List<BookingDetails> reservations=new ArrayList();
    	when(bookingDetailsRepository.getReservations(Mockito.any())).thenReturn(reservations);
    	try{
    		occupentServiceImpl.getOccupantDetails(new Date());
    		assert false;
    	}catch(ResourceNotAvailableException ex) {
    		assertEquals("No Room was booked on specified date ", ex.getMessage());
    	}
    }
    
    

    
}
