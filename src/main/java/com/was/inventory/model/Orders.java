package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Orders {

    private Integer id;

    private Customer customer;

    private Supplier supplier;

    private Set<Item> items;

    private Integer quantity;

    private Date dueDate;

    private Date date;

    public Orders() {
    }

    public Orders(Customer customer, Set<Item> items, Integer quantity, Date dueDate, Date date) {
        this.customer = customer;
        this.items = items;
        this.quantity = quantity;
        this.dueDate = dueDate;
        this.date = date;
    }

    public Orders(Supplier supplier, Set<Item> items, Integer quantity, Date dueDate, Date date) {
        this.supplier = supplier;
        this.items = items;
        this.quantity = quantity;
        this.dueDate = dueDate;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "SupplierId")
    @JsonBackReference
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToMany(mappedBy = "orders")
    @JsonBackReference
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(nullable = false)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", dueDate=" + dueDate +
                ", date=" + date +
                '}';
    }
}
