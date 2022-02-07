package com.example.atmemulator.model;


import com.example.atmemulator.security.ApplicationRole;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cvv2;
    private String cardNumber;
    private String pin;
    private LocalDateTime expirationDate;

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false)
    private ApplicationRole appRole;

    private Boolean locked = false;
    private Boolean enabled = false;
    private Integer loginAttempt = 0;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appRole.getGrantedAuthority();
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return cardNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
