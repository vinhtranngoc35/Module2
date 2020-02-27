package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBooking")
    private Long id;

    //public Date getCheckIn() {
   //     return checkIn;
   // }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Date checkIn;
    private Date checkOut;
    private LocalDate createTime;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    private boolean status;

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }
    public String getCheckIn() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(checkIn);

    }
    public void setCreateTime() {
        this.createTime = LocalDate.now();
    }

    public Booking(Date checkIn, Date checkOut, Set<BookingDetail> bookingDetailSet) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingDetailSet = bookingDetailSet;

    }


    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
       SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(checkOut);
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @OneToMany(mappedBy = "booking",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Set<BookingDetail> bookingDetailSet;

    @OneToOne(mappedBy ="booking",cascade = {CascadeType.ALL})
    private Bill bill;

    public Booking(Set<BookingDetail> bookingDetails, Bill bill) {
        this.bookingDetailSet = bookingDetails;
        this.bill= bill;
    }
    public Booking(BookingDetail bookingDetail){
        this.bookingDetailSet.add(bookingDetail);
    }
    public Booking( Bill bill) {
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BookingDetail> getBookingDetailSet() {
        return bookingDetailSet;
    }

    public void setBookingDetailSet(Set<BookingDetail> bookingDetailSet) {
        this.bookingDetailSet = bookingDetailSet;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Booking(Set<BookingDetail> bookingDetails) {
        this.bookingDetailSet = bookingDetails;
    }

    public Booking() {
    }
}
