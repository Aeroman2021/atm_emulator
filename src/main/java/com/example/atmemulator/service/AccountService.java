package com.example.atmemulator.service;

import com.example.atmemulator.model.Account;
import com.example.atmemulator.repository.AccountRepository;
import com.example.atmemulator.service.core.AbstractCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService extends AbstractCrud<Account,Integer> {
    private final AccountRepository accountRepository;

    @PostConstruct
    void init(){
        setJpaRepository(accountRepository);
    }

    public Account FindAccountByCardNumber(String cardNumber){
        return accountRepository.findAccountByCreditCard_CardNumber(cardNumber).get();
    }

    public Double CheckBalanceByCardBalnce(String cardNumber){
        return FindAccountByCardNumber(cardNumber).getBalance();
    }


}
