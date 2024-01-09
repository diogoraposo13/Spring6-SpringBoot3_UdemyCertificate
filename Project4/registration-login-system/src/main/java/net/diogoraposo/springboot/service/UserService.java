package net.diogoraposo.springboot.service;

import net.diogoraposo.springboot.dto.UserDto;
import net.diogoraposo.springboot.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
