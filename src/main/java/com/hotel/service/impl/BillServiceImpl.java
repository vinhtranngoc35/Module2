package com.hotel.service.impl;

import com.hotel.model.Bill;
import com.hotel.repository.BillRepository;
import com.hotel.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<Bill> findBillBigger() {
        return billRepository.findBillBigger();
    }
}
