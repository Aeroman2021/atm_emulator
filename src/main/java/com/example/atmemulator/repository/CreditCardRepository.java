package com.example.atmemulator.repository;

import com.example.atmemulator.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {
    Optional<CreditCard> findCreditCardByCardNumber(String cardNumber);
    Optional<CreditCard> findCreditCardByPin(String pinCode);
}
