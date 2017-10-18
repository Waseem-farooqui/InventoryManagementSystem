package com.was.inventory.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author waseem farooqui
 */

@Entity
@Table(name = "PaymentMethod")
public class PaymentMethod {

    private Integer id;

    private String name;

    private Set<Payment> payments;


    public PaymentMethod() {
    }

    public PaymentMethod(String name) {
        this.name = name;
    }


    public PaymentMethod(Integer id) {
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

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
