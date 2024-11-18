package com.learn.gateway.models;

import lombok.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refershToken;
    private long expireAt;
    private Collection<String> authorities;
}
