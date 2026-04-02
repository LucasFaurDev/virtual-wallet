package com.Lucas.virtual_wallet_server.sevices;

import com.Lucas.virtual_wallet_server.exceptions.InvalidCredentialsException;
import com.Lucas.virtual_wallet_server.models.Dto.AuthResponse;
import com.Lucas.virtual_wallet_server.models.Dto.LoginDto;
import com.Lucas.virtual_wallet_server.models.Dto.UserDto;
import com.Lucas.virtual_wallet_server.models.User;
import com.Lucas.virtual_wallet_server.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                       UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    public AuthResponse register(UserDto userDto) {
        User newUser = userService.createUser(userDto);
        String token = jwtUtils.generateToken(newUser.getUsername(), newUser.getId());

        return new AuthResponse(token, newUser.getUsername(), newUser.getId());
    }

    public AuthResponse login(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()
            ));
        } catch (AuthenticationException exception) {
            throw new InvalidCredentialsException();
        }

        User loginUser = userService.getUserByUsername(loginDto.getUsername());
        String token = jwtUtils.generateToken(loginUser.getUsername(), loginUser.getId());

        return new AuthResponse(token, loginUser.getUsername(), loginUser.getId());
    }
}
