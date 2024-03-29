package com.baeldung.spring.data.jpa.upsert;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_card_id_seq")
    @SequenceGenerator(name = "credit_card_id_seq", sequenceName = "credit_card_id_seq", allocationSize = 1)
    private Long id;
    private String cardNumber;
    private String expiryDate;

    private Long customer_id;

    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CreditCard() {
    }

    @Override
    public String toString() {
        return "CreditCard{" + "id=" + id + ", cardNumber='" + cardNumber + '\'' + ", expiryDate='" + expiryDate + '\'' + ", customer_id=" + customer_id + '}';
    }
}
