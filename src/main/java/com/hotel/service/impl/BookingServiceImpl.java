package com.hotel.service.impl;

import com.hotel.model.Bill;
import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;
import com.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Booking book) {
        bookingRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findCreateTime() {
        return bookingRepository.findCreateTime();
    }

    @Override
    public Booking findBookingByBookingDetailId(Long id) {
        return bookingRepository.findBookingByBookingDetailId(id);
    }

    @Override
    public List<Booking> findBookingToDay(Date checkIn, Date checkOut) {
        return bookingRepository.findBookingToDay(checkIn,checkOut);
    }

    @Override
    public Booking findBookingByBill(Bill bill) {
        return bookingRepository.findBookingByBill(bill);
    }

    @Override
    public Booking findBookingByCustomerIdentityCard(String customer_identityCard) {
        return bookingRepository.findBookingByCustomerIdentityCard(customer_identityCard);
    }


}
