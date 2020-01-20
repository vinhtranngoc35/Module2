package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "billDetails")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBillDetail")
    private Long id;
    private Long price;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public BillDetail(Long price, Product product, Bill bill, Long quantity) {
        this.price = price;
        this.product = product;
        this.bill = bill;
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    private Long quantity;

    public BillDetail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BillDetail(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
