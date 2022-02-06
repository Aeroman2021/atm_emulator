package com.example.atmemulator.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne
    private CreditCard sourceCard;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    @CreationTimestamp
    private LocalDateTime localDateTime;
}
