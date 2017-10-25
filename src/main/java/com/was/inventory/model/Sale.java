package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Sale")
public class Sale {

    private Integer id;

    private Set<Payment> payments;

    private Set<Item> items;


    public Sale() {
    }

    public Sale(Set<Payment> payments, Set<Item> items) {
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


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "SaleItem", joinColumns = @JoinColumn(name = "Sale_id",
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

    @OneToMany(cascade = CascadeType.ALL)//(mappedBy = "sale", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
//    @Column(nullable = false)
    @JoinColumn(name = "saleId")
    @JsonBackReference
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                '}';
    }

    public void addPayment(Payment payment){
        payment.setSale(this);
        getPayments().add(payment) ;

    }
}
