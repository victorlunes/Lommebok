package dev.lommebok.lommebok.controller.auth;

import dev.lommebok.lommebok.config.security.TokenService;
import dev.lommebok.lommebok.model.user.UserModel;
import dev.lommebok.lommebok.exception.user.UsernameOrPasswordInvalidException;
import dev.lommebok.lommebok.dto.user.request.UserRequest;
import dev.lommebok.lommebok.dto.user.response.LoginResponse;
import dev.lommebok.lommebok.dto.user.response.UserResponse;
import dev.lommebok.lommebok.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest) {
        try {
            UsernamePasswordAuthenticationToken userAndPass =
                    new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());

            Authentication authentication = authenticationManager.authenticate(userAndPass);

            UserModel user = (UserModel) authentication.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        }catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Username or password incorrect");
        }
    }
}
