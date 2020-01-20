package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCustomer")
    private Long id;

    private String identityCard;
    private String name;
    private String address;
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<BookingDetail> bookingDetailSet;

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<Booking> bookingSet;
    public Set<BookingDetail> getBookingDetailSet() {
        return bookingDetailSet;
    }

    public void setBookingDetailSet(Set<BookingDetail> bookingDetailSet) {
        this.bookingDetailSet = bookingDetailSet;
    }


    public Customer(String identityCard, String name, Set<BookingDetail> bookingDetailSet) {
        this.identityCard = identityCard;
        this.name = name;
        this.bookingDetailSet = bookingDetailSet;
    }

    public Customer(String identityCard, String name) {
        this.identityCard = identityCard;
        this.name = name;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
