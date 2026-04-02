package com.Lucas.virtual_wallet_server.models.Dto;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public class UpdateUserDto {
    private String name;
    private String lastName;
    private String socialSecurityNumber;
    private LocalDate birthdate;

    @Email(message = "Please enter a valid email")
    private String email;
    private String picture;
    private String phone;
    private String password;
    private String username;

    public UpdateUserDto() {}

    public UpdateUserDto(String name, String lastName, String socialSecurityNumber,
                         LocalDate birthdate, String email, String picture, String phone,
                         String password, String username) {
        this.name = name;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void  setSocialSecurityNumber(String socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
