package com.codegym.springtoken.service;

import com.codegym.springtoken.model.Role;
import com.codegym.springtoken.model.User;
import com.codegym.springtoken.repo.RoleRepo;
import com.codegym.springtoken.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username);
        if(user == null){
            log.info("user is not found");
            throw new UsernameNotFoundException("user not in db");
        } else {
            log.info("user is int the db {}", username);
        }
        Collection<SimpleGrantedAuthority> athorities = new ArrayList<>();
        user.getRoles().forEach(role -> athorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), athorities);
    }
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.userRepo.findByUsername(username);
        Role role = this.roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String name) {
        return this.userRepo.findByUsername(name);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepo.findAll();
    }
}
