package com.Lucas.virtual_wallet_server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String picture;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String bankKey;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({ "balance", "bankKey", "contacts", "operations", "user" })
    private Account account;

    public Contact() {}

    public Contact(String name, String lastName, String phone, String email,
                   String picture, String bankKey, String username) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
        this.bankKey = bankKey;
        this.username = username;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getBankKey() { return bankKey; }
    public void setBankKey(String bankKey) { this.bankKey = bankKey; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
