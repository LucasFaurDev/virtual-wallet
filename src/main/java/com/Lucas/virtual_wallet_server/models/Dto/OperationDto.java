package com.Lucas.virtual_wallet_server.models.Dto;

import com.Lucas.virtual_wallet_server.models.OperationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class OperationDto {

    @NotNull(message = "Type is required")
    private OperationType type;

    @NotNull(message = "The amount is required")
    @Positive(message = "The amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "The addressee is required")
    private String addressee;

    public OperationDto() {}

    public OperationDto(OperationType type, BigDecimal amount, String addressee) {
        this.type = type;
        this.amount = amount;
        this.addressee = addressee;
    }

    public OperationType getType() { return type; }
    public void setType(OperationType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getAddressee() { return addressee; }
    public void setAddressee(String addressee) { this.addressee = addressee; }
}
