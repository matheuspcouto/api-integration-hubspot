package br.com.api.integration.hubspot.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Getter
@Setter
public class TokenService {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = new Date().getTime() + expiresIn;
    }
}
