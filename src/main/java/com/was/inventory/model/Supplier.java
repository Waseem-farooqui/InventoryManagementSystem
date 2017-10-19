package com.was.inventory.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Supplier")
public class Supplier {

    private Integer id;

    private String name;

    private String contactNo;

    private String email;

    private String address;

    private Set<Payment> payments;

    private Set<Orders> orders;

    private Set<Purchase> purchases;


    public Supplier() {
    }

    public Supplier(String name, String contactNo, String email, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
