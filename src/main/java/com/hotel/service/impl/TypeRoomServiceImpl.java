package com.hotel.service.impl;

import com.hotel.model.TypeRoom;
import com.hotel.repository.TypeRoomRepository;
import com.hotel.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeRoomServiceImpl implements TypeRoomService {

    @Autowired
    TypeRoomRepository typeRoomRepository;


    @Override
    public Iterable<TypeRoom> findAll() {
        return typeRoomRepository.findAll();
    }

    @Override
    public TypeRoom findById(Long id) {
        return typeRoomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(TypeRoom typeRoom) {
        typeRoomRepository.save(typeRoom);
    }

    @Override
    public void remove(Long id) {
        typeRoomRepository.deleteById(id);
    }
}
