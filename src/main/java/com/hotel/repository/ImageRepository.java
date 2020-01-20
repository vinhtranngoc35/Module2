package com.hotel.repository;

import com.hotel.model.Image;
import com.hotel.model.TypeRoom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepository extends PagingAndSortingRepository<Image,Long> {
    Iterable<Image> findImagesByTypeRoom(TypeRoom typeRoom);
}
