package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Purchase")
public class Purchase {

    private Integer id;

    private Set<Payment> payments;

    private Set<Item> items;


    public Purchase() {
    }

    public Purchase(Set<Payment> payments, Set<Item> items) {
        this.payments = payments;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PurchaseItem", joinColumns = @JoinColumn(name = "Purchase_id",
            referencedColumnName = "id"),
            inverseJoinColumns = {
            @JoinColumn(name = "Item_name", referencedColumnName = "name"),
            @JoinColumn(name = "Item_color", referencedColumnName = "color")})
    @JsonManagedReference
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaseId")
    @JsonBackReference
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
