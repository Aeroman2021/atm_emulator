package com.example.atmemulator.jwt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PinAndCardNumberAuthenticationRequest {
    private String CardNumber;
    private String pinCode;
}
