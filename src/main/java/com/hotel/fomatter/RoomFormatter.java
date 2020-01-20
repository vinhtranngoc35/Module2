package com.hotel.fomatter;

import com.hotel.model.Room;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoomFormatter implements Formatter<Room> {
    RoomService roomService;
    @Autowired
    public RoomFormatter(RoomService roomService){this.roomService = roomService;}
    @Override
    public Room parse(String text, Locale locale) throws ParseException {
        return roomService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Room object, Locale locale) {
        return  "[" + object.getId() + ", " +object.getName() + "]";
    }
}
