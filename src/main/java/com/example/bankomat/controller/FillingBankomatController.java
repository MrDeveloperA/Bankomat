package com.example.bankomat.controller;

import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.entity.FillingBankomat;
import com.example.bankomat.entity.MoneyInBankomat;
import com.example.bankomat.entity.User;
import com.example.bankomat.payload.FillingBankomatDto;
import com.example.bankomat.repository.BankomatRepository;
import com.example.bankomat.repository.FillingBankomatRepository;
import com.example.bankomat.repository.MoneyInBankomatRepository;
import com.example.bankomat.repository.UserRepository;
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
@RequestMapping("/api/fillingBankomat")
@Service
public class FillingBankomatController {
    @Autowired
    FillingBankomatRepository fillingBankomatRepository;
    @Autowired
    BankomatRepository bankomatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MoneyInBankomatRepository moneyInBankomatRepository;

    @Autowired
    JavaMailSender javaMailSender;

    //    Filling
    @PreAuthorize(value = "hasAnyRole('ROLE_USER')")
    @PostMapping
    public HttpEntity<?> addMoney(@RequestBody FillingBankomatDto fillingBankomatDto) {

        FillingBankomat fillingBankomat = new FillingBankomat();

        Optional<User> optionalUser = userRepository.findByEmail(fillingBankomatDto.getEmail());
        if (!optionalUser.isPresent())
            return ResponseEntity.notFound().build();
        fillingBankomat.setUser(optionalUser.get());

        Optional<Bankomat> optionalBankomat = bankomatRepository.findById(fillingBankomatDto.getBankomat());
        if (!optionalBankomat.isPresent())
            return ResponseEntity.notFound().build();
        fillingBankomat.setBankomat(optionalBankomat.get());

        fillingBankomat.setThousand(fillingBankomatDto.getThousand());
        fillingBankomat.setFiveThousand(fillingBankomatDto.getFiveThousand());
        fillingBankomat.setFiveThousand(fillingBankomatDto.getThousand());
        fillingBankomat.setThousand(fillingBankomatDto.getThousand());
        fillingBankomat.setTenThousand(fillingBankomatDto.getTenThousand());
        fillingBankomat.setFiftyThousand(fillingBankomatDto.getFiftyThousand());
        fillingBankomat.setHundredThousand(fillingBankomatDto.getHundredThousand());
        fillingBankomat.setOneDollar(fillingBankomatDto.getOneDollar());
        fillingBankomat.setFiveDollar(fillingBankomatDto.getFiveDollar());
        fillingBankomat.setTenDollar(fillingBankomatDto.getTenDollar());
        fillingBankomat.setTwentyDollar(fillingBankomatDto.getTwentyDollar());
        fillingBankomat.setFiftyDollar(fillingBankomatDto.getFiftyDollar());
        fillingBankomat.setHundredDollar(fillingBankomatDto.getHundredDollar());

        Optional<MoneyInBankomat> byId = moneyInBankomatRepository.findById(optionalBankomat.get().getId());
        if (!byId.isPresent())
            return ResponseEntity.notFound().build();
        byId.get().setThousand(byId.get().getThousand() + fillingBankomat.getThousand());
        byId.get().setFiveThousand(byId.get().getFiveThousand() + fillingBankomat.getFiveThousand());
        byId.get().setTenThousand(byId.get().getTenThousand() + fillingBankomat.getTenThousand());
        byId.get().setFiftyThousand(byId.get().getFiftyThousand() + fillingBankomat.getFiftyThousand());
        byId.get().setHundredThousand(byId.get().getHundredThousand() + fillingBankomat.getHundredThousand());

        byId.get().setOneDollar(byId.get().getOneDollar() + fillingBankomat.getOneDollar());
        byId.get().setFiveDollar(byId.get().getFiveDollar() + fillingBankomat.getFiveDollar());
        byId.get().setTenDollar(byId.get().getTenDollar() + fillingBankomat.getTenDollar());
        byId.get().setTwentyDollar(byId.get().getTwentyDollar() + fillingBankomat.getTwentyDollar());
        byId.get().setFiftyDollar(byId.get().getFiftyDollar() + fillingBankomat.getFiftyDollar());
        byId.get().setHundredDollar(byId.get().getHundredDollar() + fillingBankomat.getHundredDollar());

        sendEmail(optionalUser.get().getEmail(), fillingBankomat.toString());

        FillingBankomat save = fillingBankomatRepository.save(fillingBankomat);
        return ResponseEntity.ok(save);
    }

    //    Get
    @PreAuthorize(value = "hasAnyRole('ROLE_EMPLOYEE', 'ROLE_DIRECTOR', 'ROLE_MANAGER')")
    @GetMapping
    public HttpEntity<?> getFillingBankomat() {
        return ResponseEntity.ok(fillingBankomatRepository.findAll());
    }

    //    get by id
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getFillingBankomatById(@PathVariable UUID id) {
        Optional<FillingBankomat> optionalFillingBankomat = fillingBankomatRepository.findById(id);
        return ResponseEntity.status(optionalFillingBankomat.isPresent() ? 200 : 404).body(optionalFillingBankomat.orElse(null));
    }


    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("aq9163577");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Accountni tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
