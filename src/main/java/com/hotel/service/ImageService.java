package com.hotel.service;

import com.hotel.model.Image;
import com.hotel.model.TypeRoom;

public interface ImageService {
    Iterable<Image> findAll();

    Image findById(Long id);

    void save(Image room);

    void remove(Long id);
    Iterable<Image> findImagesByTypeRoom(TypeRoom typeRoom);
}
