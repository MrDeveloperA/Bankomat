package com.example.bankomat.payload;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}
