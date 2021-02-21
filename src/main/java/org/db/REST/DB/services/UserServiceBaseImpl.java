package org.db.REST.DB.services;

import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.db.REST.DB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBaseImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return new User();

    }

    @Override
    public User create(User user) {
        return new User();
    }

    @Override
    public User replace(User user) {
        return new User();
    }

    @Override
    public void deleteById(Long id) {

    }
}
