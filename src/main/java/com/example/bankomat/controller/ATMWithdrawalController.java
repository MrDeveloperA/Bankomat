package com.example.bankomat.controller;

import com.example.bankomat.entity.*;
import com.example.bankomat.payload.ATMWithdrawalDto;
import com.example.bankomat.payload.ApiResponse;
import com.example.bankomat.payload.FillingBankomatDto;
import com.example.bankomat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ATMWithdrawal")
@Service
public class ATMWithdrawalController {
    @Autowired
    ATMWithdrawalRepository atmWithdrawalRepository;
    @Autowired
    BankomatRepository bankomatRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    MoneyInBankomatRepository moneyInBankomatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping
    public HttpEntity<?> getMoney(@RequestBody ATMWithdrawalDto atmWithdrawalDto, Integer pinCode) {

        ATMWithdrawal atmWithdrawal = new ATMWithdrawal();

        Optional<Card> optionalCard = cardRepository.findById(atmWithdrawalDto.getCard());
        if (!optionalCard.isPresent())
            return ResponseEntity.notFound().build();
        atmWithdrawal.setCard(optionalCard.get());

        Optional<Bankomat> optionalBankomat = bankomatRepository.findById(atmWithdrawalDto.getBankomat());
        if (!optionalBankomat.isPresent())
            return ResponseEntity.notFound().build();
        atmWithdrawal.setBankomat(optionalBankomat.get());

        if (optionalCard.get().isActive()
                && optionalCard.get().getPinCode().equals(pinCode)) {
            atmWithdrawal.setMoney(atmWithdrawal.getMoney());

            Optional<MoneyInBankomat> optionalMoneyInBankomat = moneyInBankomatRepository.findById(optionalBankomat.get().getId());
            if (!optionalBankomat.isPresent())
                return ResponseEntity.notFound().build();

            int hundredThousand = optionalMoneyInBankomat.get().getHundredThousand() - (atmWithdrawal.getMoney()
                    / optionalMoneyInBankomat.get().getHundredThousand());
            int fiftyThousand = (atmWithdrawal.getMoney() % optionalMoneyInBankomat.get().getHundredThousand())
                    / (optionalMoneyInBankomat.get().getFiftyThousand());
            int tenThousand = ((atmWithdrawal.getMoney() % optionalMoneyInBankomat.get().getHundredThousand())
                    % (optionalMoneyInBankomat.get().getFiftyThousand()))
                    / optionalMoneyInBankomat.get().getTenThousand();
            int thousand = (((atmWithdrawal.getMoney() % optionalMoneyInBankomat.get().getHundredThousand())
                    % (optionalMoneyInBankomat.get().getFiftyThousand()))
                    % optionalMoneyInBankomat.get().getTenThousand());

            optionalMoneyInBankomat.get().setThousand(thousand);
            optionalMoneyInBankomat.get().setTenThousand(tenThousand);
            optionalMoneyInBankomat.get().setFiftyThousand(fiftyThousand);
            optionalMoneyInBankomat.get().setHundredThousand(hundredThousand);

            ATMWithdrawal save = atmWithdrawalRepository.save(atmWithdrawal);

            return ResponseEntity.ok(save);
        }
        return ResponseEntity.ok("Pin code is wrong");
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_USER')")
    @GetMapping
    public HttpEntity<?> getATMWithdrawal() {
        return ResponseEntity.ok(atmWithdrawalRepository.findAll());
    }

    //    get by id
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getATMWithdrawalById(@PathVariable UUID id) {
        Optional<ATMWithdrawal> optionalATMWithdrawal = atmWithdrawalRepository.findById(id);
        return ResponseEntity.status(optionalATMWithdrawal.isPresent() ? 200 : 404).body(optionalATMWithdrawal.orElse(null));
    }
}
