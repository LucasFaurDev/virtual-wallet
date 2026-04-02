package com.Lucas.virtual_wallet_server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime date;
    private OperationType type;
    private BigDecimal amount;
    private String addressee;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({ "balance", "bankKey", "contacts", "operations", "user" })
    private Account account;

    public Operation() {}

    public Operation(LocalDateTime date, OperationType type, BigDecimal amount, String addressee) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.addressee = addressee;
    }

    public String getId() { return id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public OperationType getType() { return type; }
    public void setType(OperationType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getAddressee() { return addressee; }
    public void setAddressee(String addressee) { this.addressee = addressee; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}
