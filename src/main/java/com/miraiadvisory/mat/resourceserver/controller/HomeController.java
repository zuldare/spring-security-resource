package com.miraiadvisory.mat.resourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class HomeController {

    Logger log = Logger.getLogger(HomeController.class.getName());
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    Map<String, String> home(@AuthenticationPrincipal Jwt jwt){
        return Map.of("HELLO JWT claimsw", jwt.getClaims().toString());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    Map<String, String> user(@AuthenticationPrincipal Jwt jwt){
        return Map.of("Use HELLO JWT claimsw", jwt.getClaims().toString());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    Map<String, String> admin(@AuthenticationPrincipal Jwt jwt){
        return Map.of("Admin Use HELLO JWT claimsw", jwt.getClaims().toString());
    }

    @GetMapping("/adminrole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Map<String, String> adminRole(@AuthenticationPrincipal Jwt jwt){
        return Map.of("ROLE Admin Use HELLO JWT claimsw", jwt.getClaims().toString());
    }

//    @GetMapping("/athenaclient")
//    Map<String, String> athena(@RegisteredOAuth2AuthorizedClient("client-oidc")OAuth2AuthorizedClient authorizedClient){
//
//        String jwtAccessToken = authorizedClient.getAccessToken().getTokenValue();
//        log.info("JwtAccessToken " + jwtAccessToken);
//
//        String url = "http://localhost:9000/athena";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + authorizedClient.getAccessToken());
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Object>() {});
//
//
//       // return Map.of("athena", jwt.getClaims().toString());
//    }

    @GetMapping("/athena")
    @PreAuthorize("hasAnyAuthority('ATHENA')")
    Map<String, String> athena(@AuthenticationPrincipal Jwt jwt, Integer("idBusinessUnit") bussinesUnitId){

        jwt.getBusi() != busines -> ex


        return Map.of("athena", jwt.getClaims().toString());
    }

    @GetMapping("/athenarole")
    @PreAuthorize("hasAnyAuthority('ROLE_ATHENA')")
    Map<String, String> athenarole(@AuthenticationPrincipal Jwt jwt){
        return Map.of("athenaRole", jwt.getClaims().toString());
    }

    public void blau(){


    }
}
