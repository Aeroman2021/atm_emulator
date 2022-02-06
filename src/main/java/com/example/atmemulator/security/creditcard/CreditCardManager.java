package com.example.atmemulator.security.creditcard;

import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardManager implements UserDetailsService {
    private final CreditCardRepository creditCardRepository;
    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        CreditCard creditCard = creditCardRepository.findCreditCardByCardNumber(cardNumber)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return creditCard;
    }
}
