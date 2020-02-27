package com.hotel.service.impl;

import com.hotel.model.Booking;
import com.hotel.model.BookingDetail;
import com.hotel.repository.BookingDetailRepository;
import com.hotel.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class BookDetailServiceImpl implements BookingDetailService {
    @Autowired
    BookingDetailRepository bookingDetailRepository;
    @Override
    public Iterable<BookingDetail> findAll() {
        return bookingDetailRepository.findAll();
    }

    @Override
    public BookingDetail findById(Long id) {
        return bookingDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BookingDetail bookingDetail) {
        bookingDetailRepository.save(bookingDetail);
    }

    @Override
    public void remove(Long id) {
        bookingDetailRepository.deleteById(id);
    }

    @Override
    public void saveByQuery(Date checkIn, Date checkOut, Long idCustomer, Long idRoom, Long idBooking,Long price) {
        bookingDetailRepository.saveByQuery(checkIn,checkOut,idCustomer,idRoom,idBooking,price);
    }

    @Override
    public BookingDetail findBookingToday(Date checkIn, Date checkOut, Long id) {
        return bookingDetailRepository.findBookingToday(checkIn,checkOut,id);
    }

    @Override
    public List<BookingDetail> findIdBigger() {
        return bookingDetailRepository.findIdBigger();
    }
}
