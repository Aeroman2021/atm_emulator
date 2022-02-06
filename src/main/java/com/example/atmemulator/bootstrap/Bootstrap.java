package com.example.atmemulator.bootstrap;

import com.example.atmemulator.model.Account;
import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.repository.CreditCardRepository;
import com.example.atmemulator.service.AccountService;
import com.example.atmemulator.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.example.atmemulator.security.ApplicationRole.CREDIT_CARD;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class Bootstrap implements CommandLineRunner {

    private final CreditCardService creditCardService;
    private final CreditCardRepository creditCardRepository;
    private final AccountService accountService;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) throws Exception {
        log.info("Start saving a bank account");
        if (creditCardRepository.count() == 0) {
            Account account = Account.builder()
                    .balance(45000d)
                    .number("IR789456123")
                    .build();
            log.info("Start saving a bank account");
            Account savedAccount = accountService.save(account);
            CreditCard creditCard = CreditCard.builder()
                    .cardNumber("6280231462383745")
                    .cvv2("969")
                    .expirationDate(LocalDateTime.now().plusYears(1))
                    .account(savedAccount)
                    .pin(bCryptPasswordEncoder.encode("1398"))
                    .locked(false)
                    .enabled(true)
                    .appRole(CREDIT_CARD)
                    .build();
            creditCardService.save(creditCard);
            log.info("Th credit card saved to the database successfully");
        }
    }
}
