package com.example.atmemulator.dto.creditcarddto;

import com.example.atmemulator.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardInputDto {
    private String cvv2;
    private String cardNumber;
    private String pin;
    private Account account;
}
