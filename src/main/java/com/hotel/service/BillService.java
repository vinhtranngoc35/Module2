package com.hotel.service;

import com.hotel.model.Bill;
import com.hotel.model.BillDetail;

import java.util.List;

public interface BillService {
    Iterable<Bill> findAll();

    Bill findById(Long id);

    void save(Bill bill);

    void remove(Long id);
    List<Bill> findBillBigger();
}
