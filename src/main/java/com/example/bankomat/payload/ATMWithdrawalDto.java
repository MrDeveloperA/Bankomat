package com.example.bankomat.payload;

import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.entity.Card;
import com.example.bankomat.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMWithdrawalDto {
    @NotNull
    private UUID card;
    @NotNull
    private UUID bankomat;
    @Column(nullable = false)
    private Integer money;
}
