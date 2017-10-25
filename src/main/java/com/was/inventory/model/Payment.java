package com.was.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private Sale sale;

    private Purchase purchase;

    public Payment() {
    }


    public Payment(Date dueDate, Long paid, Long payable, Date date, Customer customer, PaymentMethod paymentMethod, Sale sale) {
        this.dueDate = dueDate;
        this.paid = paid;
        this.payable = payable;
        this.date = date;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.sale = sale;
    }

    public Payment(Date dueDate, Long paid, Long payable, Date date, Supplier supplier, PaymentMethod paymentMethod, Purchase purchase) {
        this.dueDate = dueDate;
        this.paid = paid;
        this.payable = payable;
        this.date = date;
        this.supplier = supplier;
        this.paymentMethod = paymentMethod;
        this.purchase = purchase;
    }

    public Payment(Long paid, Date date, Sale sale) {
        this.paid = paid;
        this.date = date;
        this.sale = sale;
    }

    public Payment(Long paid, Date date, Purchase purchase) {
        this.paid = paid;
        this.date = date;
        this.purchase = purchase;
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
    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SupplierId")
    @JsonBackReference
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
    @JsonBackReference
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "SaleId",updatable=false,insertable=false)
    @JsonManagedReference
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "PurchaseId",updatable=false,insertable=false)
    @JsonManagedReference
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
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
