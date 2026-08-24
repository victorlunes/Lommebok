package dev.lommebok.lommebok.config.security;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTUserData {
    private long userId;
    private String email;
}
