package com.dictionaryapp.service;

import com.dictionaryapp.model.dtos.UserRegisterDto;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean registerUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
        Optional<User> newUser = userRepository.findByEmail(userRegisterDto.getEmail());

        return newUser.isPresent();
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return false;
        }

        return passwordEncoder.matches(password, user.get().getPassword());
    }

    public void loginUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            loggedUser.setId(user.get().getId())
                    .setUsername(user.get().getUsername());
        }
    }
}
