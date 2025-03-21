package com.banking.banking.controller;

import com.banking.banking.event.PublishResponse;
import com.banking.banking.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("/bank")
public class BankAccountController {

    private final ObjectMapper objectMapper;
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(ObjectMapper objectMapper, BankAccountService bankAccountService) {
        this.objectMapper = objectMapper;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping(value ="/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> withdraw(@RequestParam("accountId") Long accountId, @RequestParam("amount") BigDecimal amount) {
        try {
            PublishResponse response = bankAccountService.getWithdrawFromAccount(accountId, amount);

            return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("error occurred ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
