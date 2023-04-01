package com.example.bankomat.repository;

import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.entity.MoneyInBankomat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "moneyInBankomat")
public interface MoneyInBankomatRepository extends JpaRepository<MoneyInBankomat, UUID> {

}
