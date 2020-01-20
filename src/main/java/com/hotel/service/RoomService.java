package com.hotel.service;

import com.hotel.model.prototype.ICheckTypeRoom;
import com.hotel.model.Room;
import com.hotel.model.TypeRoom;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface RoomService {
    Iterable<Room> findAll();

    Room findById(Long id);

    void save(Room room);

    void remove(Long id);
    Iterable<ICheckTypeRoom> checkRoom(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
    Iterable<Room> findByTypeRoom(TypeRoom typeRoom);
    Iterable<Room> checkTypeRoom(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut,@Param("id") Long id);
    Iterable<Room> findAllRoomIsBooking(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut,@Param("id") Long id);
    Iterable<Room> findAllRoomInDay(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
}
