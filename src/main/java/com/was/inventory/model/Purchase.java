package com.was.inventory.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Purchase")
public class Purchase {

    private Integer id;

    private Set<Supplier> suppliers;

    private Set<Item> items;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "purchases")
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }


    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PurchaseItem", joinColumns = @JoinColumn(name = "Purchase_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Item_id", referencedColumnName = "id"))
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
