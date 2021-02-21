package org.db.REST.DB;

import org.db.REST.DB.enums.Gender;
import org.db.REST.DB.exception.ExistedLoginException;
import org.db.REST.DB.exception.UnhandledException;
import org.db.REST.DB.exception.UserNotFoundException;
import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.db.REST.DB.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceBaseImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User defaultUser;
    private User userWithInvalidLogin;

    private User generateDefaultUser() {
        User alex = new User();
        alex.setFullName("alex");
        alex.setDateOfBirth(LocalDate.now());
        alex.setGender(Gender.valueOf("MALE"));
        alex.setLogin("alex@secret");
        alex.setId(0L);

        return alex;
    }

    private User generateUserWithInvalidLogin() {
        User userWithInvalidLogin = generateDefaultUser();
        userWithInvalidLogin.setId(userWithInvalidLogin.getId()+1);
        return userWithInvalidLogin;
    }

    @BeforeEach
    private void setUp() {

        defaultUser = generateDefaultUser();
        userWithInvalidLogin = generateUserWithInvalidLogin();
        String encodedInvalidLogin = userWithInvalidLogin.getLogin();


        List<User> users = new ArrayList<>();
        users.add(defaultUser);

        Mockito.when(userRepository.findAll())
                .thenReturn(users);

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(userRepository.save(userWithInvalidLogin))
                .thenThrow(RuntimeException.class);

        Mockito.when(userRepository.findAllByLogin(Mockito.any(String.class)))
                .thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findAllByLogin(encodedInvalidLogin))
                .thenReturn(users);

        Mockito.when(userRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(defaultUser));
        Mockito.when(userRepository.findById(10L))
                .thenReturn(Optional.empty());
    }



    @Test
    void getAllUsers() {

        List<User> users = userService.getAll();
        assert (users.size() == 1);
        User user = users.get(0);
        assert (user.getFullName().equals("alex"));
    }

    @Test
    void createNewUserWithUniqueLogin() {

        User roma = new User();
        roma.setFullName("roma");
        roma.setDateOfBirth(LocalDate.now());
        roma.setGender(Gender.valueOf("MALE"));
        roma.setLogin("roma@secret");

        User userAfterCreation = userService.create(roma).orElseThrow(UnhandledException::new);

        assert (userAfterCreation.getFullName().equals(roma.getFullName()));
    }

    @Test
    void createNewUserWithExistedLogin__throwExistedLoginExceptionByDefault() {
        try {
            User userAfterCreation = userService.create(userWithInvalidLogin).orElseThrow(UnhandledException::new);

            assert false;
        } catch (ExistedLoginException e) {
            assert true;
        } catch (RuntimeException e) {
            assert false;
        }

    }

    @Test
    void getExistedUserById() {
        User user = userService.getById(0L).orElseThrow(() -> new UserNotFoundException(0L));

        assert user.getFullName().equals(defaultUser.getFullName());
    }

    @Test
    void getNotExistedUserById() {
        try {
            User user = userService.getById(10L).orElseThrow(() -> new UserNotFoundException(10L));
            assert false;
        } catch(UserNotFoundException e) {
            assert true;
        }
    }
}
