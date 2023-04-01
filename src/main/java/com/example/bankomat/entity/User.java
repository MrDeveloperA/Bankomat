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
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;//userning takrorlanmas qismi

    @Column(nullable = false, length = 50)
    private String firstName;//ismi

    @Column(nullable = false)
    private String lastName;//familiyasi

    @Column(unique = true, nullable = false)
    private String email;//email
    @Column(nullable = false)
    private String password;//password
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;//qachon ro'yxatdan o'tganligi
    @UpdateTimestamp
    private Timestamp updatedAt;//ohirgi marta qachon tahrirlanganligi


    @ManyToMany

    private Set<Role> roles;
    private boolean accountNonExpired = true; // bu userning amal qilish muddati
    private boolean accountNonLocked = true; //accound qulflanmaganligi
    private boolean credentialsNonExpired = true;
    private boolean enabled;
    private String emailCode;

    // User detailsning methodlari:

    // userning xuquqlari royxati
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    //    userning usernameini qaytaruvchi method
    @Override
    public String getUsername() {
        return this.email;
    }

    //amal qilish muddatini qaytarish
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    // account bloklanganligini qaytaradi
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    // accountning ishonchlilik muddati tugagan yoki tugamaganligini qaytaradi
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    // accountning activligini bildiradi
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
