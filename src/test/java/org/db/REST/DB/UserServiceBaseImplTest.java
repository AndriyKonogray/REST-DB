package org.db.REST.DB;

import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.db.REST.DB.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceBaseImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User alex = new User();
        alex.setFullName("alex");

        List<User> users = new ArrayList<>();
        users.add(alex);

        Mockito.when(userRepository.findAll())
                .thenReturn(users);
    }

    @Test
    void getAllUsers() {

        List<User> users = userService.getAll();
        User user = users.get(0);
        assert (user.getFullName().equals("alex"));
    }
}
