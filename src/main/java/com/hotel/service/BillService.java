package com.hotel.service;

import com.hotel.model.Bill;
import com.hotel.model.BillDetail;
import com.hotel.model.Booking;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillService {
    Iterable<Bill> findAll();

    Bill findById(Long id);

    void save(Bill bill);

    void remove(Long id);
    List<Bill> findBillBigger();
    void saveBillQuery(@Param("idBooking")Long id,@Param("idCustomer")Long idCustomer);
    Bill findBillByBooking(Booking booking);
}
