package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRoom")
    private Long id;
    private String name;
    private boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<BookingDetail> bookingDetails;
    public Room(String name, boolean status, Set<BookingDetail> bookingDetails, TypeRoom typeRoom) {
        this.name = name;
        this.status = status;
        this.bookingDetails = bookingDetails;
        this.typeRoom = typeRoom;
    }

    public Set<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Set<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "typeroom_id")
    private TypeRoom typeRoom;

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public Room() {
    }
    public Room(String name){
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
