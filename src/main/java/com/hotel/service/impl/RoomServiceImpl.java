package com.hotel.service.impl;

import com.hotel.model.prototype.ICheckTypeRoom;
import com.hotel.model.Room;
import com.hotel.model.TypeRoom;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Iterable<ICheckTypeRoom> checkRoom(Date checkIn, Date checkOut) {
        return roomRepository.checkRoom(checkIn,checkOut);
    }

    @Override
    public Iterable<Room> findByTypeRoom(TypeRoom typeRoom) {
        return roomRepository.findAllByTypeRoom(typeRoom);
    }

    @Override
    public Iterable<Room> checkTypeRoom(Date checkIn, Date checkOut,Long id) {
        return roomRepository.checkTypeRoom(checkIn,checkOut,id);
    }

    @Override
    public Iterable<Room> findAllRoomIsBooking(Date checkIn, Date checkOut, Long id) {
        return roomRepository.findAllRoomIsBooking(checkIn,checkOut,id);
    }

    @Override
    public Iterable<Room> findAllRoomInDay(Date checkIn, Date checkOut) {
        return roomRepository.findAllRoomInDay(checkIn,checkOut);
    }

}
