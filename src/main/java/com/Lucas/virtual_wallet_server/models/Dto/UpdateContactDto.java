package com.Lucas.virtual_wallet_server.models.Dto;

public class UpdateContactDto {

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String picture;
    private String username;

    public UpdateContactDto() {}

    public UpdateContactDto(String name, String lastName, String phone, String email,
                            String picture, String username) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
        this.username = username;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
