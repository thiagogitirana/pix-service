package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<AccountJpa, Integer> {

    Optional<AccountJpa> findByBranchNumberAndAccountNumber(Integer branchNumber, Integer accountNumber);
}
