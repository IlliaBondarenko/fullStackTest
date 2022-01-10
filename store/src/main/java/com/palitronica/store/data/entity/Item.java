package com.palitronica.store.data.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotNull
    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "itemPrice", columnDefinition = "FLOAT")
    private Float itemPrice;

    public Long getitemId() {
        return itemId;
    }

    public void setitemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getitemName() {
        return itemName;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getitemPrice() {
        return itemPrice;
    }

    public void setitemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
