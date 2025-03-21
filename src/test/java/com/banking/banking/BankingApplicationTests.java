package com.banking.banking;

import com.banking.banking.service.BankAccountService;
import com.banking.banking.service.SnsClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingApplicationTests {

	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private SnsClient snsClient;
	@Test
	void contextLoads() {
	}

}
