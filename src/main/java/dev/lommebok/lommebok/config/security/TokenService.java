package dev.lommebok.lommebok.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.lommebok.lommebok.model.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT
                .create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("lommebok")
                .sign(algorithm);
    }


    public Optional<JWTUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            Long userId = jwt.getClaim("userId").asLong();
            String name = jwt.getClaim("name").asString();
            String subject = jwt.getSubject();

            return Optional.of(
                    new JWTUserData(userId, name, subject)
            );

        }catch (JWTVerificationException exception){
            return Optional.empty();
        }
    }
}
