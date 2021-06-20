package com.onemount.onecinema.controller;

import java.util.List;

import javax.validation.Valid;

import com.onemount.onecinema.model.Booking;
import com.onemount.onecinema.request.BookingRequest;
import com.onemount.onecinema.response.BookingInfo;
import com.onemount.onecinema.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {
  @Autowired private BookingService bookingService;

  @PostMapping("/ticket")
  public ResponseEntity<Booking> createBooking(@ModelAttribute @Valid BookingRequest bookingRequest) {
    return ResponseEntity.ok().body(bookingService.createBooking(bookingRequest));
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookingInfo>> getBookingInfo() {
    return ResponseEntity.ok().body(bookingService.getBookingInfo());
  }

  @GetMapping("/ticket")
  public String showBookingForm(
  @ModelAttribute BookingRequest bookingRequest,  
  BindingResult bindingResult, 
  Model model) {
    if (! bindingResult.hasErrors()) {
      model.addAttribute("bookingRequest", bookingRequest); 
    }    
    return "bookTicket";
  }
}
