package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="booking_id")
    private Booking booking;
    public Booking getBooking() {
        return booking;
    }
    public BookingDetail(Date checkInExpected, Date checkOutExpected, Room room, Booking booking, List<Customer> customer) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.room = room;
        this.booking = booking;
        this.customers = customer;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @OneToMany(mappedBy = "bookingDetail")
    private List<Customer> customers =new ArrayList<Customer>();;

    public BookingDetail(List<Customer> customer) {
        this.customers = customer;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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



    public BookingDetail(Customer customer){
       this.customers = new ArrayList<Customer>();
        this.customers.add(customer);
    }
    public BookingDetail(){
    }
    public BookingDetail(Date checkInExpected, Date checkOutExpected, Room room) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.room = room;
    }

    public BookingDetail(Date checkInExpected, Date checkOutExpected, Date checkIn, Date checkOut, List<Customer> customer, Room room) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customers = customer;
        this.room = room;
    }

    public BookingDetail(Date checkInExpected, Date checkOutExpected, Long price, Room room, List<Customer> customer) {
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.price = price;
        this.room = room;
        this.customers = customer;
    }

    public BookingDetail(Long id, Date checkInExpected, Date checkOutExpected, Date checkIn, Date checkOut, Long price, List<Customer> customer) {
        this.id = id;
        this.checkInExpected = checkInExpected;
        this.checkOutExpected = checkOutExpected;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.customers = customer;

    }
}
