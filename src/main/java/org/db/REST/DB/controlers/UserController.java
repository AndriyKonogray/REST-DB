package org.db.REST.DB.controlers;

import org.db.REST.DB.DTOs.UserDTO;
import org.db.REST.DB.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return userService.getAll();
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    UserDTO replaceUser(@RequestBody UserDTO userDTO) {
        return userService.replace(userDTO);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
