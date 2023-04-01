package com.example.bankomat.repository;

import com.example.bankomat.entity.Address;
import com.example.bankomat.entity.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, UUID> {

}
