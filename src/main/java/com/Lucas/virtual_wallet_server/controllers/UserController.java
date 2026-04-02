package com.Lucas.virtual_wallet_server.controllers;

import com.Lucas.virtual_wallet_server.models.Dto.UpdateUserDto;
import com.Lucas.virtual_wallet_server.models.User;
import com.Lucas.virtual_wallet_server.sevices.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserByUsername(username)
        );
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto,
                                           Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.updateUser(updateUserDto, username)
        );
    }

    @DeleteMapping()
    public ResponseEntity<Void> removeUser(Authentication authentication) {
        String username = authentication.getName();
        userService.removeUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
