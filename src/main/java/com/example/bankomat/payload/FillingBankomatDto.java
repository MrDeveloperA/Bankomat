package com.example.bankomat.payload;

import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillingBankomatDto extends User {
    @NotNull
    private UUID user;
    @NotNull
    private UUID bankomat;

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

}
