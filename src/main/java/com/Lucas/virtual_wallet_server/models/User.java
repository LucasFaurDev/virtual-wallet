package com.Lucas.virtual_wallet_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String lastName;
    private String socialSecurityNumber;
    private LocalDate birthdate;
    private String email;
    private String picture;
    private String phone;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;

    public User() {}

    public User(String name, String lastName, String socialSecurityNumber,
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
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void setSocialSecurityNumber(String socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public boolean getIsAccountNonExpired() { return isAccountNonExpired; }
    public void setIsAccountNonExpired(boolean isAccountNonExpired) { this.isAccountNonExpired = isAccountNonExpired; }

    public boolean getIsAccountNonLocked() { return isAccountNonLocked; }
    public void setIsAccountNonLocked(boolean isAccountNonLocked) { this.isAccountNonLocked = isAccountNonLocked; }

    public boolean getIsCredentialsNonExpired() { return isCredentialsNonExpired; }
    public void setIsCredentialsNonExpired(boolean isCredentialsNonExpired) { this.isCredentialsNonExpired = isCredentialsNonExpired; }

    public boolean getIsEnabled() { return isEnabled; }
    public void setIsEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        return List.of(authority);
    }

    @Override
    public boolean isAccountNonExpired() { return isAccountNonExpired; }

    @Override
    public boolean isAccountNonLocked() { return isAccountNonLocked; }

    @Override
    public boolean isCredentialsNonExpired() { return isCredentialsNonExpired; }

    @Override
    public boolean isEnabled() { return isEnabled; }

}
