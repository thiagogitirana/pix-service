package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PixKeyRepositoryJpa  extends JpaRepository<PixKeyJpa, Integer>  {

    boolean existsByValue(String value);

    @Query(value = "SELECT * " +
            "from pix_key pk " +
            "inner join key_type kt on kt.key_type_id = pk.key_type_id " +
            "inner join account acc on acc.account_id = pk.account_id " +
            "inner join client cli on cli.client_id = acc.client_id " +
            "where pk.pix_key_id = (?1)", nativeQuery = true)
    Optional<PixKeyJpa> findBypixKeyId(UUID pixKeyId);

}
