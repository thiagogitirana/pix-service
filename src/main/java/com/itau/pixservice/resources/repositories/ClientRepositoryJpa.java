package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientJpa, Integer> {

    @Query(value = "SELECT count(pk.value)  " +
            "from pix_key pk " +
            "inner join key_type kt on kt.key_type_id = pk.key_type_id " +
            "inner join account acc on acc.account_id = pk.account_id " +
            "inner join client cli on cli.client_id = acc.client_id " +
            "where cli.first_name = (?1) and cli.last_name = (?2)", nativeQuery = true)
    int countClientKeys(String firstName, String lastName);
    Optional<ClientJpa> findByFirstNameAndLastName(String firstName, String lastName);
}
