package com.example.atmemulator.dto.transaction;

import com.example.atmemulator.model.Account;
import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.model.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInputDto {
    private String token;
    private Double amount;
}
