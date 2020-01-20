package com.hotel.repository;

import com.hotel.model.BillDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillDetailRepository extends PagingAndSortingRepository<BillDetail,Long> {
}
