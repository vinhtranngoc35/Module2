package com.hotel.controller;

import com.hotel.model.*;
import com.hotel.model.prototype.ICheckTypeRoom;
import com.hotel.service.BookingService;
import com.hotel.service.ImageService;
import com.hotel.service.RoomService;
import com.hotel.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = {("*")})
public class HomeApiController {
    @Autowired
    TypeRoomService typeRoomService;
    @Autowired
    RoomService roomService;
    @Autowired
    ImageService imageService;
    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/bookings/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Booking>> listAllCustomers() {
        Iterable<Booking> bookings =  bookingService.findAll();
        if (bookings==null) {
            return new ResponseEntity<Iterable<Booking>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<Booking>>(bookings, HttpStatus.OK);
    }
    @RequestMapping(value = "/images/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Image>> listAllImages() {
        Iterable<Image> images =  imageService.findAll();
        if (images==null) {
            return new ResponseEntity<Iterable<Image>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<Image>>(images, HttpStatus.OK);
    }
    @RequestMapping(value = "/rooms/{checkIn}/{checkOut}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ICheckTypeRoom>> listRoom(@PathVariable("checkIn")Date checkIn, @PathVariable("checkOut") Date checkOut){
        Iterable<ICheckTypeRoom> rooms = roomService.checkRoom(checkIn,checkOut);
        return new ResponseEntity<Iterable<ICheckTypeRoom>>(rooms,HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Room>> listTypeRoom(@PathVariable("id")Long id){
        Iterable<Room> rooms = roomService.findByTypeRoom(typeRoomService.findById(id));

        if (rooms==null){
            return new ResponseEntity<Iterable<Room>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Room>>(rooms,HttpStatus.OK);
    }
    @RequestMapping(value = "/rooms/{checkIn}/{checkOut}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Room>> listRoomOfTypeRoom(@PathVariable("checkIn")Date checkIn, @PathVariable("checkOut") Date checkOut,@PathVariable("id")Long id){
        Iterable<Room> rooms = roomService.checkTypeRoom(checkIn,checkOut,id);
        return new ResponseEntity<Iterable<Room>>(rooms,HttpStatus.OK);
    }
    @RequestMapping(value = "/bookingOfToDay/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Booking>> listBooking(){
        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(date.toString());
        List<Booking> bookings =bookingService.findBookingToDay(Date.valueOf(LocalDate.now()),Date.valueOf(formatter.format(date.getTime()+86400000)));
        return new ResponseEntity<Iterable<Booking>>(bookings,HttpStatus.OK);
    }

}
