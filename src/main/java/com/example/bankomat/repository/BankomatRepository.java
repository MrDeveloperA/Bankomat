package com.example.bankomat.repository;

import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.entity.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "bankomat")
public interface BankomatRepository extends JpaRepository<Bankomat, UUID> {

}
