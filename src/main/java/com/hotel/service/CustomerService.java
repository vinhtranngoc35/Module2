package com.hotel.service;

import com.hotel.model.BookingDetail;
import com.hotel.model.Customer;
import com.hotel.repository.CustomerRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    void save(Customer book);

    void remove(Long id);
    List<Customer> findAllByQuery();
    void updateDetailByQuery(@Param("bookingDetail_id")Long bookingDetailId, @Param("idCustomer")Long customerId);
    List<Customer> findByBookingDetail(BookingDetail bookingDetail);
}
