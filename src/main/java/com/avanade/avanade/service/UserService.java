package com.avanade.avanade.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avanade.avanade.dto.UserDTO;
import com.avanade.avanade.entity.User;
import com.avanade.avanade.repository.UserRepository;


@Service
public class UserService {
    
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserDTO create(UserDTO dto) {
        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = this.repository.save(user);
        return user.dto();
    }


    public UserDTO getOne(Long id) {
        Optional<User> userOp = this.repository.findById(id);
        return userOp.get().dto();
    }


    public List<UserDTO> getAll() {
        return this.repository.findAll().stream().map(user -> user.dto()).toList();
    }

    public UserDTO edit(UserDTO dto, Long id) {
        User user = new User(dto, id);
        user = this.repository.save(user);
        return user.dto();
    }

    public void delete(Long id) {
        this.repository.deleteById(id);

    }
}
