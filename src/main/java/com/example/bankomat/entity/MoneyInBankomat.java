package com.example.bankomat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MoneyInBankomat {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private Bankomat bankomat;
    private Integer thousand;
    private Integer fiveThousand;
    private Integer tenThousand;
    private Integer fiftyThousand;
    private Integer hundredThousand;
    private Integer oneDollar;
    private Integer fiveDollar;
    private Integer tenDollar;
    private Integer twentyDollar;
    private Integer fiftyDollar;
    private Integer hundredDollar;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
