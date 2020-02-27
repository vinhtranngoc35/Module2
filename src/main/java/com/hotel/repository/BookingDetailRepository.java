package com.hotel.repository;

import com.hotel.model.Booking;
import com.hotel.model.BookingDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

public interface BookingDetailRepository extends PagingAndSortingRepository<BookingDetail,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into bookingdetails (checkInExpected,checkOutExpected,customer_id,room_id,booking_id,price) values (:checkIn,:checkOut,:idCustomer,:idRoom,:idBooking,:price)",nativeQuery = true)
    void saveByQuery(@Param("checkIn")Date checkIn,@Param("checkOut") Date checkOut,@Param("idCustomer")Long idCustomer,@Param("idRoom")Long idRoom,@Param("idBooking")Long idBooking,@Param("price")Long price);
    @Query(value = "select * from bookingdetails where (checkInExpected<=:checkOut and checkOutExpected>=:checkIn and checkOut is null and room_id=:id )",nativeQuery = true)
    BookingDetail findBookingToday(@Param("checkIn")Date checkIn,@Param("checkOut")Date checkOut,@Param("id")Long id);
    @Query(value = "select * from bookingdetails order by idBookingDetail desc",nativeQuery = true)
    List<BookingDetail> findIdBigger();

}
