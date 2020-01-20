package com.hotel.service;

import com.hotel.model.Bill;
import com.hotel.model.Booking;
import com.hotel.model.BookingDetail;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface BookingService {
    Iterable<Booking> findAll();

    Booking findById(Long id);

    void save(Booking book);

    void remove(Long id);
    List<Booking> findCreateTime();
    Booking findBookingByBookingDetailId(@Param("id")Long id);
    List<Booking> findBookingToDay(@Param("checkInInput") Date checkIn,@Param("checkOutInput") Date checkOut);
    Booking findBookingByBill(Bill bill);
    Booking findBookingByCustomerIdentityCard(String customer_identityCard);
}
