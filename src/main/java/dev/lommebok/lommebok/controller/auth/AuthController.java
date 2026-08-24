package dev.lommebok.lommebok.controller.user;

import dev.lommebok.lommebok.config.security.TokenConfig;
import dev.lommebok.lommebok.dto.user.request.LoginRequest;
import dev.lommebok.lommebok.dto.user.request.RegisterUserRequest;
import dev.lommebok.lommebok.dto.user.response.LoginResponse;
import dev.lommebok.lommebok.dto.user.response.RegisterUserResponse;
import dev.lommebok.lommebok.model.user.UserModel;
import dev.lommebok.lommebok.repository.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            TokenConfig tokenConfig
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequestDTO
    ) {
        UsernamePasswordAuthenticationToken userAndPass =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(userAndPass);

        UserModel user = (UserModel) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);


        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(
            @Valid @RequestBody RegisterUserRequest registerUserRequestDTO
    ) {
        UserModel newUserModel = new UserModel();

        newUserModel.setName(registerUserRequestDTO.getName());
        newUserModel.setEmail(registerUserRequestDTO.getEmail());
        newUserModel.setPassword(
                passwordEncoder.encode(registerUserRequestDTO.getPassword())
        );

        userRepository.save(newUserModel);

        RegisterUserResponse response = new RegisterUserResponse(
                newUserModel.getEmail(),
                newUserModel.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}