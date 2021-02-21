package org.db.REST.DB.controlers;

import org.db.REST.DB.DTOs.UserDTO;
import org.db.REST.DB.enums.Gender;
import org.db.REST.DB.exception.UnhandledException;
import org.db.REST.DB.exception.UserNotFoundException;
import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users", consumes = MediaType.APPLICATION_JSON_VALUE)
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
        return toDTO(userService.getById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PostMapping
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return toDTO(userService.create(toEntity(userDTO)).orElseThrow(UnhandledException::new));
    }

    @PutMapping("/{id}")
    UserDTO replaceUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {

        Optional<User> optional = userService.getById(id);
        User user = toEntity(userDTO);

        if (optional.isEmpty()) {
            return toDTO(userService.create(user).orElseThrow(UnhandledException::new));
        }

        user.setId(id);
        return toDTO(userService.replace(user).orElseThrow(UnhandledException::new));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), null, user.getFullName(), user.getDateOfBirth(), user.getGender().toString());
    }

    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setFullName(userDTO.getFullName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(Gender.valueOf(userDTO.getGender()));

        return user;
    }

}
