package com.hotel.service;

import com.hotel.model.BillDetail;
import com.hotel.model.Image;
import com.hotel.model.TypeRoom;

public interface BillDetailService {
    Iterable<BillDetail> findAll();

    BillDetail findById(Long id);

    void save(BillDetail billDetail);

    void remove(Long id);

}
