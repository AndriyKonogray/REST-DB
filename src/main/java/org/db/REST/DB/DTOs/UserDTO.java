package org.db.REST.DB.DTOs;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserDTO {

    private long id;
    private String login;
    private String fullName;

    @DateTimeFormat
    private LocalDate dateOfBirth;
    private String gender;

    public UserDTO() {}

    public UserDTO(long id, String login, String fullName, LocalDate dateOfBirth, String gender) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public long getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
