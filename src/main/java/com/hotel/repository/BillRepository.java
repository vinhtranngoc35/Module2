package com.hotel.repository;

import com.hotel.model.Bill;
import com.hotel.model.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BillRepository extends PagingAndSortingRepository<Bill,Long> {
    @Query(value = "select * from bills order by idBill desc",nativeQuery = true)
    List<Bill> findBillBigger();
    @Transactional
    @Modifying
    @Query(value ="INSERT INTO bills (booking_idBooking,customer_id)\n" +
            "VALUES (:idBooking,:idCustomer);\n",nativeQuery = true)
    void saveBillQuery(@Param("idBooking")Long id,@Param("idCustomer")Long idCustomer);
    Bill findBillByBooking(Booking booking);
}
