package org.db.REST.DB.services;

import org.db.REST.DB.exception.ExistedLoginException;
import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.db.REST.DB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getById(@NonNull Long id) {
        return userRepository.findById(id);

    }

    @Override
    public Optional<User> create(@NonNull User user) {

        if(isExistedLogin(user.getLogin())) {
            throw new ExistedLoginException();
        }

        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<User> replace(@NonNull User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public void deleteById(@NonNull Long id) {
        userRepository.deleteById(id);
    }

    private Boolean isExistedLogin(@NonNull String login) {
        List<User> users = userRepository.findAllByLogin(login);
        return !users.isEmpty();
    }
}
