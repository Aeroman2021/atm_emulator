package com.example.atmemulator.dto.transaction;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckBalanceTransactionInputDto {
    private String token;
}
