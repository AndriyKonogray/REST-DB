package org.db.REST.DB.interfaces;

import org.db.REST.DB.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User create(User user);
    User replace(User user);
    void deleteById(Long id);

}
