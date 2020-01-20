package com.hotel.service;

import com.hotel.model.TypeRoom;

public interface TypeRoomService {
    Iterable<TypeRoom> findAll();

    TypeRoom findById(Long id);

    void save(TypeRoom typeRoom);

    void remove(Long id);
}
