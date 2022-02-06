package com.example.atmemulator.service;

import com.example.atmemulator.dto.creditcarddto.CreditCardOutputDto;
import com.example.atmemulator.dto.transaction.CheckBalanceTransactionInputDto;
import com.example.atmemulator.dto.transaction.TransactionInputDto;
import com.example.atmemulator.dto.transaction.TransactionOutputDto;
import com.example.atmemulator.exceptions.CreditCardException;
import com.example.atmemulator.exceptions.NoSuficientBalanceException;
import com.example.atmemulator.model.Account;
import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.model.Transaction;
import com.example.atmemulator.model.TransactionType;
import com.example.atmemulator.repository.TransactionRepository;
import com.example.atmemulator.service.core.AbstractCrud;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class TransactionService extends AbstractCrud<Transaction, Integer> {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CreditCardService creditCardService;
    private final SecretKey secretKey;


    @PostConstruct
    void init() {
        setJpaRepository(transactionRepository);
    }

    @Transactional
    public TransactionOutputDto Deposit(TransactionInputDto transactionInputDto) {
        String cardNumber = extractCardNumberFromToken(transactionInputDto.getToken());
        CreditCard foundCard = creditCardService.findCardByNumber(cardNumber);
        Account account = foundCard.getAccount();
        Double amount = transactionInputDto.getAmount();
        double updatedBalance = (account.getBalance()) + amount;
        account.setBalance(updatedBalance);
        accountService.update(account);
        Transaction transaction = convertInputToEntity(amount,TransactionType.DEPOSIT,foundCard);
        Transaction savedTransaction = super.save(transaction);
        return convertEntityToOutputDto(savedTransaction);
    }

    @Transactional
    public TransactionOutputDto Withdrawal(TransactionInputDto transactionInputDto) {
        String cardNumber = extractCardNumberFromToken(transactionInputDto.getToken());
        CreditCard foundCard = creditCardService.findCardByNumber(cardNumber);
        Account account = foundCard.getAccount();
        Double amount = transactionInputDto.getAmount();
        if (BalanceIsSufficient(account, amount)) {
            double updatedBalance = (account.getBalance()) - amount;
            account.setBalance(updatedBalance);
            accountService.update(account);
            Transaction transaction = convertInputToEntity(amount,TransactionType.WITHDRAWAL,foundCard);
            Transaction savedTransaction = super.save(transaction);
            return convertEntityToOutputDto(savedTransaction);
        } else
            throw new NoSuficientBalanceException("There is no sufficient credit in the account");
    }

    public TransactionOutputDto CheckBalance(String token) {
        String cardNumber = extractCardNumberFromToken(token);
        CreditCard foundCard = creditCardService.findCardByNumber(cardNumber);
        Account account = foundCard.getAccount();
        Double balance = account.getBalance();
        Transaction transaction = convertInputToEntity(balance,TransactionType.CHECK_BALANCE,foundCard);
        Transaction savedTransaction = super.save(transaction);
        return convertEntityToOutputDto(savedTransaction);
    }


    public Boolean BalanceIsSufficient(Account account, Double amount) {
        return account.getBalance() > amount;
    }

    public Transaction convertInputToEntity(Double amount,TransactionType type,CreditCard card) {
        return Transaction.builder()
                .amount(amount)
                .transactionType(type)
                .sourceCard(card)
                .build();
    }


    public TransactionOutputDto convertEntityToOutputDto(Transaction transaction) {
        return TransactionOutputDto.builder()
                .Id(transaction.getId())
                .amount(transaction.getAmount())
                .cardNumber(transaction.getSourceCard().getCardNumber())
                .transactionType(transaction.getTransactionType())
                .build();
    }



    private String extractCardNumberFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        String subject = body.getSubject();
        return subject;
    }
}
