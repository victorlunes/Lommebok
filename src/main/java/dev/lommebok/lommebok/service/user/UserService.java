package dev.lommebok.lommebok.service.user;

import dev.lommebok.lommebok.model.user.UserModel;
import dev.lommebok.lommebok.mapper.user.UserMapper;
import dev.lommebok.lommebok.repository.user.UserRepository;
import dev.lommebok.lommebok.dto.user.request.UserRequest;
import dev.lommebok.lommebok.dto.user.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        UserModel newUser = userMapper.toUser(userRequest);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);

        return userMapper.toUserResponse(newUser);
    }

}
