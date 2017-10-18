package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer") //maps the entity with the table. If no @Table is defined,
// the default value is used: the class name of the entity.
public class Customer {

    private Integer id;

    private Information information;

    private Set<Payment> payments;

    private Set<Sale> sales;

    private Set<Orders> orders;

    public Customer() {
    }

    public Customer(Information information) {
        this.information = information;
    }



    /**
     *
     * @return
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(name = "Information_id")
    @JsonManagedReference(value = "customer-reference")
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CustomerSales", joinColumns = @JoinColumn(name = "Customer_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Sale_id", referencedColumnName = "id"))
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return String.format("Customer{id = %d, " +
                "name = %s, " +
                "contact_no = %s, " +
                "address = %s, " +
                "email = %s}",
                id,information.getName(), information.getContactNo(), information.getAddress(), information.getEmail());
    }
}
