package com.example.atmemulator.service;

import com.example.atmemulator.dto.creditcarddto.CreditCardInputDto;
import com.example.atmemulator.dto.creditcarddto.CreditCardOutputDto;
import com.example.atmemulator.exceptions.NotFoundException;
import com.example.atmemulator.model.CreditCard;
import com.example.atmemulator.repository.CreditCardRepository;
import com.example.atmemulator.service.core.AbstractCrud;
import com.example.atmemulator.service.core.EntityConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditCardService extends AbstractCrud<CreditCard, Integer>
        implements EntityConvertor<CreditCardInputDto, CreditCard, CreditCardOutputDto> {

    private final CreditCardRepository creditCardRepository;

    @PostConstruct
    void init() {
        setJpaRepository(creditCardRepository);
    }


    public CreditCard findCardByPinCode(String pinCode) {
        return creditCardRepository
                .findCreditCardByPin(pinCode)
                .orElseThrow(() -> new NotFoundException("Invalid pincode"));
    }

    public CreditCard findCardByNumber(String number) {
        return creditCardRepository
                .findCreditCardByCardNumber(number)
                .orElseThrow(() -> new NotFoundException("Invalid number"));
    }

    public Boolean CreditCardIsValid(String cardNumber){
        return findCardByPinCode(cardNumber) != null;
    }


    @Override
    public CreditCard convertInputToEntity(CreditCardInputDto creditCardInputDto) {
        return CreditCard.builder()
                .cardNumber(creditCardInputDto.getCardNumber())
                .cvv2(creditCardInputDto.getCvv2())
                .pin(creditCardInputDto.getPin())
                .account(creditCardInputDto.getAccount())
                .expirationDate(LocalDateTime.now().plusYears(1)).build();
    }

    @Override
    public CreditCardOutputDto convertEntityToOutputDto(CreditCard creditCard) {
        return CreditCardOutputDto.builder()
                .id(creditCard.getId())
                .cardNumber(creditCard.getCardNumber())
                .build();
    }



}
