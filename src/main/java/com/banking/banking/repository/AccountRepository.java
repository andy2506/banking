package com.banking.banking.repository;

import com.banking.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById(int id);
    Account findByAccountId(Long accountNumber);
}
