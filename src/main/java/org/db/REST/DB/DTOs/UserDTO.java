package org.db.REST.DB.DTOs;

public class UserDTO {

    private final long id;
    private final String login;
    private final String fullName;
    private final String dateOfBirth;
    private final String gender;

    public UserDTO(long id, String login, String fullName, String dateOfBirth, String gender) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
}
