package com.was.inventory.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Sale")
public class Sale {

    private Integer id;

    private Set<Customer> customers;

    private Set<Item> items;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "sales")
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SaleItem", joinColumns = @JoinColumn(name = "Sale_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Item_id", referencedColumnName = "id"))
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                '}';
    }
}
