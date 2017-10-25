package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Item")
public class Item {


//   private Integer id;

//    private String name;

    private Category category;

    private ItemsKey id;

    private String description;

//    private String color;

    private Long sellingPrice;

    private Long buyingPrice;

    private Integer quantity;

    private String pictureLink;

    private Set<Sale> sales;

    private Set<Orders> orders;

    private Set<Purchase> purchases;


    public Item() {
    }

//    public Item(String name, Category category, String description, String color, Long sellingPrice, Long buyingPrice, Integer quantity) {
//        this.name = name;
//        this.category = category;
//        this.description = description;
//        this.color = color;
//        this.sellingPrice = sellingPrice;
//        this.buyingPrice = buyingPrice;
//        this.quantity = quantity;
//    }


    public Item(ItemsKey id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Item(Category category, String description, ItemsKey itemsKey, Long sellingPrice, Long buyingPrice, Integer quantity) {
        this.category = category;
        this.description = description;
        this.id = itemsKey;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
    }

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    @JsonBackReference
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }


    @EmbeddedId
    public ItemsKey getId() {
        return id;
    }

    public void setId(ItemsKey id) {
        this.id = id;
    }

    public Long getBuyingPrice() {
        return buyingPrice;
    }


    public void setBuyingPrice(Long buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ItemOrders", joinColumns = {
            @JoinColumn(name = "Item_name", referencedColumnName = "name"),
            @JoinColumn(name = "Item_color", referencedColumnName = "color")},
            inverseJoinColumns = {@JoinColumn(name = "Orders_id", referencedColumnName = "id")})
    @JsonManagedReference
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

}
