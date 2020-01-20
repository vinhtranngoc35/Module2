package com.hotel.model;

import com.hotel.model.prototype.ICheckTypeRoom;

public class CheckTypeRoom  implements ICheckTypeRoom {
    private String name;
    private int count;
    private Long idTypeRoom;
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CheckTypeRoom(int count, Long price, String name, Long idTypeRoom) {
        this.name = name;
        this.count = count;
        this.idTypeRoom = idTypeRoom;
        this.price = price;
    }


    public CheckTypeRoom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getIdTypeRoom() {
        return idTypeRoom;
    }

    public void setIdTypeRoom(Long idTypeRoom) {
        this.idTypeRoom = idTypeRoom;
    }
}
