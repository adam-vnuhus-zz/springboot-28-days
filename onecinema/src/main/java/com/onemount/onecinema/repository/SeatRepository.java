package com.onemount.onecinema.repository;

import java.util.List;

import com.onemount.onecinema.model.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Long>{
  @Query(value= """
  SELECT s FROM seat s 
  INNER JOIN s.room r 
  WHERE r.id = :id AND 
  s.name IN :seatNames
  """)
  List<Seat> findByRoomIdAndNameIn(
    @Param("id") long room,
    @Param("seatNames") String[] seatNames
  );
}
