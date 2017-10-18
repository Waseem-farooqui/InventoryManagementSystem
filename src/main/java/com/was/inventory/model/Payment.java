package com.was.inventory.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Payment")
public class Payment {

    private Integer id;

    private Date dueDate;

    private Long paid;// When you are in debit you have to pay to supplier

    private Long payable; // When you have to take from customer.

    private Date date;

    private Customer customer;

    private Supplier supplier;

    private PaymentMethod paymentMethod;

    public Payment() {
    }

    public Payment(Date dueDate, Long paid, Long payable, Date date, Customer customer, PaymentMethod paymentMethod) {
        this.dueDate = dueDate;
        this.paid = paid;
        this.payable = payable;
        this.date = date;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }

    public Payment(Date dueDate, Long paid, Long payable, Date date, Supplier supplier, PaymentMethod paymentMethod) {
        this.dueDate = dueDate;
        this.paid = paid;
        this.payable = payable;
        this.date = date;
        this.supplier = supplier;
        this.paymentMethod = paymentMethod;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getPaid() {
        return paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getPayable() {
        return payable;
    }

    public void setPayable(Long payable) {
        this.payable = payable;
    }


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CustomerId")//mappedBy indicates the entity is the inverse of the relationship.
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SupplierId")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne (cascade = {CascadeType.MERGE})
    @JoinColumn(name = "PaymentMethodId")
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", paid=" + paid +
                ", payable=" + payable +
                ", date=" + date +
                '}';
    }
}
