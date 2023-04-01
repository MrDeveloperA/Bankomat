package com.example.bankomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bankomat {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private TypeCard bankName;
    @Column(nullable = false)
    private Integer amountCashMoney;
    @Column(nullable = false)
    private Integer amountCommissionMoney;
    @Column(nullable = false)
    private Integer amountCompleteMoney; // hisobni to'ldirgandagi comissiya miqdori
    @Column(nullable = false)
    private Integer restMoneyInBankomat;
    @OneToOne
    private Address address;



    @Column(nullable = false)
    private String lastName;//familiyasi

    @Column(nullable = false)
    private Integer pinCode;// 4 xona
    @Column(nullable = false)
    private Timestamp validityPeriod;
    @OneToOne
    private TypeCard typeCard;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private boolean active;
}
