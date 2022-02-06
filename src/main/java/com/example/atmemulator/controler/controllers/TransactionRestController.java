package com.example.atmemulator.controler.controllers;

import com.example.atmemulator.controler.api.core.ServiceResult;
import com.example.atmemulator.dto.transaction.CheckBalanceTransactionInputDto;
import com.example.atmemulator.dto.transaction.TransactionInputDto;
import com.example.atmemulator.dto.transaction.TransactionOutputDto;
import com.example.atmemulator.service.TransactionService;
import com.example.atmemulator.utility.BearerTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm/service")
@RequiredArgsConstructor
public class TransactionRestController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    ResponseEntity<ServiceResult<TransactionOutputDto>> Deposit
            (@RequestBody TransactionInputDto transactionInputDto) {
        String token = BearerTokenUtil.getBearerTokenHeader();
        transactionInputDto.setToken(token);
        TransactionOutputDto result = transactionService.Deposit(transactionInputDto);
        return ResponseEntity.ok(ServiceResult.Success(result));
    }

    @PostMapping("/withdrawal")
    ResponseEntity<ServiceResult<TransactionOutputDto>>Withdrawal
            (@RequestBody TransactionInputDto transactionInputDto){
        String token = BearerTokenUtil.getBearerTokenHeader();
        transactionInputDto.setToken(token);
        TransactionOutputDto result = transactionService.Withdrawal(transactionInputDto);
        return ResponseEntity.ok(ServiceResult.Success(result));
    }

    @GetMapping("/balancecheck")
    ResponseEntity<ServiceResult<TransactionOutputDto>>BalanceCheck(){
        String token = BearerTokenUtil.getBearerTokenHeader();
        TransactionOutputDto result = transactionService.CheckBalance(token);
        return ResponseEntity.ok(ServiceResult.Success(result));
    }

}
