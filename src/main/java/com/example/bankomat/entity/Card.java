package com.example.bankomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 50)
    private String bankName;
    @Column(nullable = false, length = 50)
    private Integer CVVCode;// 3 xonali son
    @Column(nullable = false, length = 50)
    private String firstName;//ismi

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
