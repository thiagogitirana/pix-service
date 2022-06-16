package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.enums.KeyType;
import com.itau.pixservice.resources.repositories.adapters.KeyTypeAdapter;
import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
public class PixKeyDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Predicate idPredicate;
    private Predicate keyTypePredicate;
    private Predicate branchPredicate;
    private Predicate accountPredicate;
    private Predicate namePredicate;
    private Predicate createdAtPredicate;
    private Predicate updatedAtPredicate;
    private List<Predicate> predicates;
    @Autowired
    private KeyTypeAdapter keyTypeAdapter;

    public List<PixKeyJpa> find(String id, String tipoChave, Integer agencia, Integer conta, String nome,
                                LocalDateTime inclusao, LocalDateTime inativacao) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PixKeyJpa> criteriaQuery = criteriaBuilder.createQuery(PixKeyJpa.class);

        Root<PixKeyJpa> pixKeyJpaRoot = criteriaQuery.from(PixKeyJpa.class);
        Join<PixKeyJpa, AccountJpa> accountJpaJoin = pixKeyJpaRoot.join("account");
        Join<AccountJpa, ClientJpa> clientJpaJoin = accountJpaJoin.join("client");

        criteriaQuery.where(buildRestrictions(criteriaBuilder, pixKeyJpaRoot, accountJpaJoin, clientJpaJoin, id, tipoChave,
                agencia, conta, nome, inclusao, inativacao));

        TypedQuery<PixKeyJpa> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    private Predicate[] buildRestrictions(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot,
                                          Join<PixKeyJpa, AccountJpa> accountJpaJoin, Join<AccountJpa, ClientJpa> clientJpaJoin,
                                          String id, String tipoChave, Integer agencia, Integer conta, String nome,
                                          LocalDateTime inclusao, LocalDateTime inativacao) {
        predicates = new ArrayList<>();

        buildPredicates(criteriaBuilder, pixKeyJpaRoot, accountJpaJoin, clientJpaJoin, id, tipoChave,
                agencia, conta, nome, inclusao, inativacao);

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void buildPredicates(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot,
                                 Join<PixKeyJpa, AccountJpa> accountJpaJoin, Join<AccountJpa, ClientJpa> clientJpaJoin,
                                 String id, String tipoChave, Integer agencia, Integer conta, String nome,
                                 LocalDateTime inclusao, LocalDateTime inativacao) {

        buildId(criteriaBuilder, pixKeyJpaRoot, id);
        buildKeyType(criteriaBuilder, pixKeyJpaRoot, tipoChave);
        buildBranchAndAccount(criteriaBuilder, accountJpaJoin, agencia, conta);
        buildName(criteriaBuilder, clientJpaJoin, nome);
        buildCreatedAt(criteriaBuilder, pixKeyJpaRoot, inclusao);
        buildUpdatedAt(criteriaBuilder, pixKeyJpaRoot, inativacao);
    }

    private void buildId(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot, String id) {
        if (isNotBlank(id)) {
            idPredicate = criteriaBuilder.equal(pixKeyJpaRoot.get("pixKeyId"), UUID.fromString(id));
            predicates.add(idPredicate);
        }
    }

    private void buildKeyType(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot, String tipoChave) {
        if (isNotBlank(tipoChave)) {
            keyTypePredicate = criteriaBuilder.equal(pixKeyJpaRoot.get("keyType"), keyTypeAdapter.toJpa(KeyType.valueOf(tipoChave)));
            predicates.add(keyTypePredicate);
        }

    }

    private void buildBranchAndAccount(CriteriaBuilder criteriaBuilder, Join<PixKeyJpa, AccountJpa> accountJpaJoin, Integer agencia, Integer conta) {
        if (agencia != null) {
            branchPredicate = criteriaBuilder.equal(accountJpaJoin.get("branchNumber"), agencia);
            predicates.add(branchPredicate);
        }

        if (conta != null) {
            accountPredicate = criteriaBuilder.equal(accountJpaJoin.get("accountNumber"), conta);
            predicates.add(accountPredicate);
        }

    }

    private void buildName(CriteriaBuilder criteriaBuilder, Join<AccountJpa, ClientJpa> clientJpaJoin, String nome) {
        if (isNotBlank(nome)) {
            namePredicate = criteriaBuilder.equal(clientJpaJoin.get("firstName"), nome);
            predicates.add(namePredicate);
        }

    }

    private void buildCreatedAt(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot, LocalDateTime inclusao) {
        if (inclusao != null) {
            createdAtPredicate = criteriaBuilder.greaterThanOrEqualTo(pixKeyJpaRoot.get("createdAt"), inclusao);
            predicates.add(createdAtPredicate);
        }

    }

    private void buildUpdatedAt(CriteriaBuilder criteriaBuilder, Root<PixKeyJpa> pixKeyJpaRoot, LocalDateTime inativacao) {
        if (inativacao != null) {
            updatedAtPredicate = criteriaBuilder.greaterThanOrEqualTo(pixKeyJpaRoot.get("updatedAt"), inativacao);
            predicates.add(updatedAtPredicate);
        }
    }
}
