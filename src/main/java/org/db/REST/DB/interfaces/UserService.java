package org.db.REST.DB.interfaces;

import org.db.REST.DB.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> create(User user);
    Optional<User> replace(User user);
    void deleteById(Long id);

}
