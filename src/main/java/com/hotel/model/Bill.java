package com.hotel.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBill;
    private Long total;
    private Long extra;
    private Long priceRoom;

    public Long getExtra() {
        return extra;
    }

    public void setExtra(Long extra) {
        this.extra = extra;
    }

    public Long getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(Long priceRoom) {
        this.priceRoom = priceRoom;
    }

    public Long getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Long numberRoom) {
        this.numberRoom = numberRoom;
    }

    private boolean status= false;
    private Long numberRoom;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getTotal() {

        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER)
    private Set<Booking> bookingSet;

    public Bill(Set<Booking> bookingSet, Set<BillDetail> billDetailSet) {
        this.bookingSet = bookingSet;
        this.billDetailSet = billDetailSet;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER)
    private Set<BillDetail> billDetailSet;

    public Bill(Set<BillDetail> billDetailSet) {
        this.billDetailSet = billDetailSet;
    }

    public Bill() {
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public Set<BillDetail> getBillDetailSet() {
        return billDetailSet;
    }

    public void setBillDetailSet(Set<BillDetail> billDetailSet) {
        this.billDetailSet = billDetailSet;
    }


}
