package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bookingDetails")
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBookingDetail")
    private Long id;
    private Date checkInExpected;
    private Date checkOutExpected;
    private Date checkIn;
    private Date checkOut;
    private Long price;
    private Long numberDate;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getNumberDate() {
        return numberDate;
    }

    public void setNumberDate(Long numberDate) {
        this.numberDate = numberDate;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="booking_id")
    @JsonBackReference
    @JsonIgnore
    private Booking booking;
    public Booking getBooking() {
        return booking;
    }
    public BookingDetail(Date checkInExpected, Date checkOutExpected, Room room, Booking booking, Customer customer) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.room = room;
        this.booking = booking;
        this.customer = customer;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public BookingDetail(Customer customer) {
        this.customer = customer;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckInExpected() {
        return checkInExpected;
    }

    public void setCheckInExpected(Date checkInExpected) {
        this.checkInExpected = checkInExpected;
    }

    public Date getCheckOutExpected() {
        return checkOutExpected;
    }

    public void setCheckOutExpected(Date checkOutExpected) {
        this.checkOutExpected = checkOutExpected;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }





    public BookingDetail(Date checkInExpected, Date checkOutExpected, Date checkIn, Date checkOut, Room room) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }




    public BookingDetail(){}
    public BookingDetail(Date checkInExpected, Date checkOutExpected, Room room) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.room = room;
    }

    public BookingDetail(Date checkInExpected, Date checkOutExpected, Date checkIn, Date checkOut, Customer customer, Room room) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customer = customer;
        this.room = room;
    }

    public BookingDetail(Date checkInExpected, Date checkOutExpected, Long price, Room room, Customer customer) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.price = price;
        this.room = room;
        this.customer = customer;
    }

    public BookingDetail(Long id, Date checkInExpected, Date checkOutExpected, Date checkIn, Date checkOut, Long price, Customer customer) {
        this.id = id;
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.customer = customer;

    }
}
