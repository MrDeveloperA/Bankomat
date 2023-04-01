package com.example.bankomat.repository;

import com.example.bankomat.entity.ATMWithdrawal;
import com.example.bankomat.entity.FillingBankomat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ATMWithdrawalRepository extends JpaRepository<ATMWithdrawal, UUID> {

}
