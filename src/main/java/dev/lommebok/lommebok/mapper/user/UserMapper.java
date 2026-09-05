package dev.lommebok.lommebok.mapper.user;

import dev.lommebok.lommebok.model.user.UserModel;
import dev.lommebok.lommebok.dto.user.request.UserRequest;
import dev.lommebok.lommebok.dto.user.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toUser(UserRequest userRequest) {
        UserModel user = new UserModel();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public UserResponse toUserResponse(UserModel user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
