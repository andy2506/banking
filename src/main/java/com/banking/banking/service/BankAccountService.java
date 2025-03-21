package com.banking.banking.service;

import com.banking.banking.entity.Account;
import com.banking.banking.event.PublishRequest;
import com.banking.banking.event.PublishResponse;
import com.banking.banking.event.WithdrawalEvent;
import com.banking.banking.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    private final SnsClient snsClient;

    private final AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BankAccountService(SnsClient snsClient, AccountRepository accountRepository, JdbcTemplate jdbcTemplate) {
        this.snsClient = snsClient;
        this.accountRepository = accountRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public PublishResponse getWithdrawFromAccount(Long accountId, BigDecimal amount) {
        try {
//            // Check current balance
//            String sql = "SELECT balance FROM accounts WHERE id = ?";
//            BigDecimal currentBalance = jdbcTemplate.queryForObject(sql, new Object[]{accountId}, BigDecimal.class);
//            if (currentBalance != null && currentBalance.compareTo(amount) >= 0) {
//// Update balance
//                sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
//                int rowsAffected = jdbcTemplate.update(sql, amount, accountId);
//
//                if (rowsAffected > 0) {
//                    getPublishResponse(accountId, amount);
//                    PublishResponse.builder().response("Withdrawal successful")
//                            .build();
//                } else {
//// In case the update fails for reasons other than a balance check
//                    getPublishResponse(accountId, amount);
//                    return PublishResponse.builder()
//                            .response("Withdrawal failed")
//                            .build();
//                }
//            } else {
//// Insufficient funds
//                getPublishResponse(accountId, amount);
//                return PublishResponse.builder()
//                        .response("Insufficient funds for withdrawal")
//                        .build();
//            }
//// After a successful withdrawal, publish a withdrawal event to SNS
//        } catch (Exception e) {
//            log.error("Error {}", e.getMessage());
//        }
//        return null;



                Account account = accountRepository.findByAccountId(accountId);
                if (account != null) {
                    if (account.getBalance() > 0) {
                        //Compute
                        getPublishResponse(account.getAccountId(), account.getAmount());
                        return PublishResponse.builder().response("Withdrawal successful")
                                .build();
                    } else {
                        //Fail
                        getPublishResponse(accountId, amount);
                        return PublishResponse.builder()
                                .response("Withdrawal failed")
                                .build();
                    }
                } else {
                getPublishResponse(accountId, amount);
                return PublishResponse.builder()
                        .response("Insufficient funds for withdrawal")
                        .build();
            }
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }
        return null;

    }

    private PublishResponse getPublishResponse(Long accountId, BigDecimal amount) {
        WithdrawalEvent event = new WithdrawalEvent(amount, accountId, "SUCCESSFUL");
        String eventJson = event.toJson(); // Convert event to JSON,
        String snsTopicArn = "arn:aws:sns:YOUR_REGION:YOUR_ACCOUNT_ID:YOUR_TOPIC_NAME";
        PublishRequest publishRequest = PublishRequest.builder()
                .message(eventJson)
                .topicArn(snsTopicArn)
                .build();
        PublishResponse publishResponse = snsClient.publish("");
        return PublishResponse.builder().build();
    }

}
