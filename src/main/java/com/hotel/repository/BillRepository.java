package com.hotel.repository;

import com.hotel.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BillRepository extends PagingAndSortingRepository<Bill,Long> {
    @Query(value = "select * from bills order by idBill desc",nativeQuery = true)
    List<Bill> findBillBigger();
}
