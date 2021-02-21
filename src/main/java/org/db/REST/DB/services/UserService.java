package org.db.REST.DB.services;

import org.db.REST.DB.DTOs.UserDTO;
import org.db.REST.DB.models.User;
import org.db.REST.DB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        UserDTO userDTO = new UserDTO(1, "username@password", "Andrii Konohrai", "1995-11-16", "M");

        List<UserDTO> usersDTO = new ArrayList<>();

        usersDTO.add(userDTO);
        usersDTO.add(userDTO);

        return usersDTO;
    }

    public UserDTO getById(Long id) {
        return new UserDTO(id, "username@password", "Andrii Konohrai", "1995-11-16", "M");

    }

    public UserDTO create(UserDTO userDTO) {
        return new UserDTO(userDTO.getId(), "username@password", "Andrii Konohrai", "1995-11-16", "M");
    }

    public UserDTO replace(UserDTO userDTO) {
        return new UserDTO(userDTO.getId(), "username@password", "Andrii Konohrai", "1995-11-16", "M");
    }

    public void deleteById(Long id) {

    }
}
