package com.codegym.springtoken.service;
import com.codegym.springtoken.model.Role;
import com.codegym.springtoken.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String name);
    List<User> getUsers();
}
