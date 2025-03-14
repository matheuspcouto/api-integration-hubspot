package com.api.integration.hubspot.service;

import com.api.integration.hubspot.dto.request.AuthTokenDtoRequest;
import com.api.integration.hubspot.dto.response.AuthTokenDtoResponse;

import com.api.integration.hubspot.exceptions.ServiceUnavailableException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class AuthService {

    @Value(value = "${api.hubspot.auth.url}")
    private String authUrl;

    @Value(value = "${api.hubspot.url}")
    private String apiUrl;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthTokenDtoRequest authTokenRequest;

    public String getAutenticationUrl() throws ServiceUnavailableException {
        String url = getAuthUrl() + "/authorize";
        url += "?client_id=" + authTokenRequest.getClientId();
        url += "&redirect_uri=" + authTokenRequest.getRedirectUri();
        url += "&scope=" + authTokenRequest.getScope();

        return url;
    }

    public void oauthCallback(String code) throws ServiceUnavailableException {
        String url = getApiUrl() + "/oauth/v1/token";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("charset", "UTF-8");
        headers.add("Accept", "*/*");

        String formParameters = String.format(
                "grant_type=%s&code=%s&client_id=%s&client_secret=%s&redirect_uri=%s",
                "authorization_code",
                code,
                authTokenRequest.getClientId(),
                authTokenRequest.getClientSecret(),
                authTokenRequest.getRedirectUri()
        );

        HttpEntity<String> request = new HttpEntity<>(formParameters, headers);

        ResponseEntity<AuthTokenDtoResponse> response = new RestTemplate().postForEntity(
                url,
                request,
                AuthTokenDtoResponse.class
        );

        tokenService.setAccessToken(response.getBody().getAccessToken());
        tokenService.setRefreshToken(response.getBody().getRefreshToken());
        tokenService.setExpiresIn(response.getBody().getExpiresIn());
    }

}
