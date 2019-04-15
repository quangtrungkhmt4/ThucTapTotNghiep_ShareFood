package com.sharefood.ShareFood.service.impl;

import com.sharefood.ShareFood.model.User;
import com.sharefood.ShareFood.repository.UserRepository;
import com.sharefood.ShareFood.service.base.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserNameAndPass(String username, String pass) {
        return userRepository.findUserByUserNameAndPass(username, pass);
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<User> gettAll() {
        return (Collection<User>) userRepository.findAll();
    }
}
