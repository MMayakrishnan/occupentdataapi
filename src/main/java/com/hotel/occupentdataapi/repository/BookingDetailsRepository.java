package com.hotel.occupentdataapi.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.occupentdataapi.pojo.BookingDetails;


@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer>{
	@Query(" select b from BookingDetails b where PARSEDATETIME(FORMATDATETIME(b.departure, 'yyyy-MM-dd'), 'yyyy-MM-dd')>=?1 and PARSEDATETIME(FORMATDATETIME(b.arrival, 'yyyy-MM-dd'), 'yyyy-MM-dd')<=?1")
	public List<BookingDetails> getReservations(String date);
}
