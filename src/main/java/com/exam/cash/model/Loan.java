package com.exam.cash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "total")
    private float total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Loan() {
        total = 0;
    }

    public Loan(Long id, float total) {
        this.id = id;
        this.total = total;
    }

    public Loan(Long id, float total, User user) {
        this.id = id;
        this.total = total;
        this.user = user;
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }
}
