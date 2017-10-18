package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Supplier")
public class Supplier {

    private Integer id;

    private Information information;

    private Set<Payment> payments;

    private Set<Orders> orders;

    private Set<Purchase> purchases;


    public Supplier() {
    }

    public Supplier(Information information) {
        this.information = information;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "InformationId")
    @JsonManagedReference(value = "supplier-reference")
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SupplierPurchases", joinColumns = @JoinColumn(name = "Supplier_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Purchase_id", referencedColumnName = "id"))
    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }
}
