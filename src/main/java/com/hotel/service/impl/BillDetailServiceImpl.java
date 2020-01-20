package com.hotel.service.impl;

import com.hotel.model.BillDetail;
import com.hotel.repository.BillDetailRepository;
import com.hotel.repository.BillRepository;
import com.hotel.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public Iterable<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail findById(Long id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public void remove(Long id) {
        billDetailRepository.deleteById(id);
    }
}
