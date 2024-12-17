package org.springboot.userservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springboot.userservice.request.UserRequest;
import org.springboot.userservice.user.Role;
import org.springboot.userservice.user.UserApp;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//this class is used to convert from user request to  userapp and user app to user response!! (optional)
@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public UserApp toUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return UserApp.builder()
                .id(userRequest.id())
                .name(userRequest.name())
                .username(userRequest.username())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(Role.USER)
                .email(userRequest.email())
                .address(userRequest.address())
                .build();
    }

}
