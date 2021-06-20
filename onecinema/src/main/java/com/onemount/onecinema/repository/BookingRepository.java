package com.onemount.onecinema.repository;

import java.util.List;

import com.onemount.onecinema.model.Booking;
import com.onemount.onecinema.response.BookingInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long>{
  @Query("""
  SELECT new com.onemount.onecinema.response.BookingInfo(b.id,
  c.mobile, 
  c.email, 
  f.title, 
  cine.name, 
  e.date, 
  e.beginAt, 
  b.seats, 
  b.totalAmount, 
  b.createdOn) 
  FROM booking b 
  INNER JOIN b.customer c 
  INNER JOIN b.event e 
  INNER JOIN e.film f 
  INNER JOIN e.room r 
  INNER JOIN r.cinema cine
  """)
  List<BookingInfo> getBookingInfo();
}
