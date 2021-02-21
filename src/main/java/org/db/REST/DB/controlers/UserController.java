package org.db.REST.DB.controlers;

import org.db.REST.DB.DTOs.UserDTO;
import org.db.REST.DB.enums.Gender;
import org.db.REST.DB.exception.UnhandledException;
import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDTO> getUsers() {
        return userService.getAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return toDTO(userService.getById(id).orElseThrow(UnhandledException::new));
    }

    @PostMapping
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return toDTO(userService.create(toEntity(userDTO)).orElseThrow(UnhandledException::new));
    }

    @PutMapping("/{id}")
    UserDTO replaceUser(@RequestBody UserDTO userDTO) {
        return toDTO(userService.replace(toEntity(userDTO)).orElseThrow(UnhandledException::new));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getFullName(), user.getDateOfBirth().toString(), user.getGender().toString());

    }

    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setFullName(userDTO.getFullName());
        user.setDateOfBirth(Date.valueOf(userDTO.getDateOfBirth()));
        user.setGender(Gender.valueOf(userDTO.getGender()));

        return user;
    }

}
