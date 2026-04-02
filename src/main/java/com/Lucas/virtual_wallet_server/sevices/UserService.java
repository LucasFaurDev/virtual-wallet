package com.Lucas.virtual_wallet_server.sevices;

import com.Lucas.virtual_wallet_server.exceptions.UserNameUniqueException;
import com.Lucas.virtual_wallet_server.exceptions.UserNotFoundException;
import com.Lucas.virtual_wallet_server.models.Account;
import com.Lucas.virtual_wallet_server.models.Dto.UpdateUserDto;
import com.Lucas.virtual_wallet_server.models.Dto.UserDto;
import com.Lucas.virtual_wallet_server.models.User;
import com.Lucas.virtual_wallet_server.repository.UserRepository;
import com.Lucas.virtual_wallet_server.utils.BankKeyUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BankKeyUtil bankKeyUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       BankKeyUtil bankKeyUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bankKeyUtil = bankKeyUtil;
    }

    public User createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserNameUniqueException(userDto.getUsername());
        }

        User newUser = new User(userDto.getName(), userDto.getLastName(), userDto.getSocialSecurityNumber(),
                userDto.getBirthdate(), userDto.getEmail(), userDto.getPicture(), userDto.getPhone(),
                userDto.getPassword(), userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Account newAccount = new Account();

        newAccount.setUser(newUser);
        String bankKey = bankKeyUtil.createBankKey();
        newAccount.setBankKey(bankKey);
        newUser.setAccount(newAccount);

        return userRepository.save(newUser);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    public User updateUser(UpdateUserDto updateUserDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );

        if (!user.getUsername().equals(updateUserDto.getUsername())) {
            if (userRepository.findByUsername(updateUserDto.getUsername()).isPresent()) {
                throw new UserNameUniqueException(updateUserDto.getUsername());
            }
        }

        user.setName(updateUserDto.getName());
        user.setLastName(updateUserDto.getLastName());
        user.setSocialSecurityNumber(updateUserDto.getSocialSecurityNumber());
        user.setBirthdate(updateUserDto.getBirthdate());
        user.setEmail(updateUserDto.getEmail());
        user.setPicture(updateUserDto.getPicture());
        user.setPhone(updateUserDto.getPhone());
        user.setUsername(updateUserDto.getUsername());
        if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        }

        return userRepository.save(user);
    }

    public void removeUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
