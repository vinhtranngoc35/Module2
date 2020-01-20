package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.model.BookingDetail;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface BookingDetailService {
    Iterable<BookingDetail> findAll();

    BookingDetail findById(Long id);

    void save(BookingDetail book);

    void remove(Long id);
    void saveByQuery(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut, @Param("idCustomer")Long idCustomer, @Param("idRoom")Long idRoom, @Param("idBooking")Long idBooking,@Param("price")Long price);
    BookingDetail findBookingToday(@Param("checkIn")Date checkIn, @Param("checkOut")Date checkOut, @Param("id")Long id);
}
