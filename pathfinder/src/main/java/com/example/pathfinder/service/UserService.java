package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);
    void logoutUser();

    UserServiceModel findById(Long id);
}
