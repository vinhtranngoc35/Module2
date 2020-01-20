package com.hotel.repository;

import com.hotel.model.Bill;
import com.hotel.model.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

public interface BookingRepository extends PagingAndSortingRepository<Booking,Long> {
    @Transactional
    @Modifying
    @Query(value = "select * from bookings order by bookings.idBooking desc",nativeQuery = true)
    List<Booking> findCreateTime();
    @Query(value = "SELECT bookings.idBooking,bookings.checkIn,bookings.checkOut,bookings.createTime,bookings.bill_id,bookings.status,bookings.total,bookings.customer_id FROM bookingdetails inner join bookings on booking_id=idBooking where idBookingDetail=:id",nativeQuery = true)
    Booking findBookingByBookingDetailId(@Param("id")Long id);
    @Query(value = "select * from bookings where checkIn<=:checkOut and checkOut>=:checkIn and status=0", nativeQuery = true)
    List<Booking> findBookingToDay(@Param("checkIn") Date checkIn,@Param("checkOut") Date checkOut);
    Booking findBookingByBill(Bill bill);
    Booking findBookingByCustomerIdentityCard(String customer_identityCard);
}
