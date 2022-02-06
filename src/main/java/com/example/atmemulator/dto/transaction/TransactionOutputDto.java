package com.example.atmemulator.dto.transaction;

import com.example.atmemulator.dto.creditcarddto.CreditCardOutputDto;
import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.model.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionOutputDto {
    private Integer Id;
    private String cardNumber;
    private Double amount;
    private TransactionType transactionType;
}
