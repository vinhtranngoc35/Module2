package com.hotel.repository;

import com.hotel.model.BookingDetail;
import com.hotel.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    @Transactional
    @Modifying
    @Query(value = "select * from customers order by idCustomer desc",nativeQuery = true)
    List<Customer> findAllByQuery();
    @Transactional
    @Modifying
    @Query(value = "UPDATE customers SET bookingDetail_id = :bookingDetail_id WHERE idCustomer= :idCustomer;",nativeQuery = true)
    void updateDetailByQuery(@Param("bookingDetail_id")Long bookingDetail_id,@Param("idCustomer")Long idCustomer);
    List<Customer> findByBookingDetail(BookingDetail bookingDetail);
}
