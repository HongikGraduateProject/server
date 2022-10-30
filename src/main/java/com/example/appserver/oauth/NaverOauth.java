package com.example.appserver.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOauth implements SocialOauth {

    @Value("${spring.security.oauth2.client.registration.naver.url}")
    private String NAVER_LOGIN_URL;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.naver.callback-url}")
    private String NAVER_CALLBACK_URL;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    private final ObjectMapper objectMapper;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("response_type", "code");
        params.put("client_id", NAVER_CLIENT_ID);
        params.put("state","test");
        params.put("redirect_uri", NAVER_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));
        log.info("parametere = {}", parameterString);
        String redirectURL = NAVER_LOGIN_URL + "?" + parameterString;
        log.info("redirect url = {}",redirectURL);
        return redirectURL;
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        String NAVER_TOKEN_REQUEST_URL="https://nid.naver.com/oauth2.0/token";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", NAVER_CLIENT_ID);
        params.put("client_secret", NAVER_CLIENT_SECRET);
        params.put("code", code);
        params.put("state","test");

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        String tokenURL = NAVER_TOKEN_REQUEST_URL + "?" + parameterString;

        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(NAVER_TOKEN_REQUEST_URL,params,String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(tokenURL, String.class);
        if(responseEntity.getStatusCode()== HttpStatus.OK){
            return responseEntity;
        }
        return null;
    }

    public NaverOAuthToken getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        NaverOAuthToken naverOAuthToken = objectMapper.readValue(response.getBody(), NaverOAuthToken.class);
        return naverOAuthToken;
    }

    public ResponseEntity<String> requestUserInfo(NaverOAuthToken naverOAuthToken) {
        String NAVER_USERINFO_REQUEST_URL="https://openapi.naver.com/v1/nid/me";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        log.info(naverOAuthToken.getAccess_token());
        headers.add("Authorization", "Bearer " + naverOAuthToken.getAccess_token());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(NAVER_USERINFO_REQUEST_URL, HttpMethod.GET, httpEntity, String.class);
        return response;
    }

    public NaverUser getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException {
        NaverResponses naverResponses = objectMapper.readValue(userInfoRes.getBody(), NaverResponses.class);
        return naverResponses.response;
    }
}
