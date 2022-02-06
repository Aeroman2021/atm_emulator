package com.example.atmemulator.utility;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BearerTokenUtil {

    public static String getBearerTokenHeader() {
        String token = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getHeader("Authorization");
        return token.replace("Bearer ", "");
    }

}
