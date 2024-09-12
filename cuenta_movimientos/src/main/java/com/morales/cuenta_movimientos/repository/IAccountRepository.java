package com.morales.cuenta_movimientos.repository;

import com.morales.cuenta_movimientos.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByStatusTrue();

    Optional<Account> findByAccountNumberAndStatusTrue(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

}
