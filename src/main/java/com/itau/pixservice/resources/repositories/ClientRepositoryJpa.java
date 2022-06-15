package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientJpa, Integer> {
}
