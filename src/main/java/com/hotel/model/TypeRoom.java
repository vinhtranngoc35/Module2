package com.hotel.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "typerooms")
public class TypeRoom {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypeRoom")
    private Long id;
    private String name;
    private String imageBig;
    @JsonIgnore
    @OneToMany(mappedBy = "typeRoom")
    private Set<Image> img;

    private String description;
    private Long price;
    @JsonIgnore
    @OneToMany(mappedBy = "typeRoom")
    private Set<Room> rooms;

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public TypeRoom(String name) {
        this.name = name;
    }


    public TypeRoom() {
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public TypeRoom(String name, Set<Image> img, String description, Long price, Set<Room> room, String imageBig) {
        this.name = name;
        this.img = img;
        this.description = description;
        this.price = price;
        this.rooms = room;
        this.imageBig = imageBig;
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

    public Set<Image> getImg() {
        return img;
    }

    public void setImg(Set<Image> img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


}
