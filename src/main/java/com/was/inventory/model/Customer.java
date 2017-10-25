package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer") //maps the entity with the table. If no @Table is defined,
// the default value is used: the class name of the entity.
public class Customer {

    private Integer id;

    private String name;

    private String contactNo;

    private String email;

    private String address;

    private Set<Payment> payments;

    private Set<Orders> orders;


    public Customer() {
    }

    public Customer(String name, String contactNo, String email, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }

    public Customer(Integer id) {
        this.id = id;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "contact_no", nullable = false, unique = true)
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Column(name = "email", nullable = false, unique = true)
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
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
                id,name, contactNo, address, email);
    }
}
