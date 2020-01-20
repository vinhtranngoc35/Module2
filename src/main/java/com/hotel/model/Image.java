package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    public Image() {
    }

    public Image(String url, TypeRoom typeRoom) {
        this.url = url;
        this.typeRoom = typeRoom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idImage")
    private Long id;
    private String url;
    private String descriptions;

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @ManyToOne
    @JoinColumn(name = "typeroom_id")
    private TypeRoom typeRoom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }
}
