package com.Lucas.virtual_wallet_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(unique = true)
    private String bankKey;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("date DESC")
    private List<Operation> operations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Account() {}

    public Account(BigDecimal balance, String bankKey, List<Contact> contacts, List<Operation> operations) {
        this.balance = balance;
        this.bankKey = bankKey;
        this.contacts = contacts;
        this.operations = operations;
    }

    public String getId() { return id; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getBankKey() { return bankKey; }
    public void setBankKey(String bankKey) { this.bankKey = bankKey; }

    public List<Contact> getContacts() { return contacts; }
    public void setContacts(List<Contact> contacts) { this.contacts = contacts; }

    public List<Operation> getOperations() { return operations; }
    public void setOperations(List<Operation> operations) { this.operations = operations; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
