package mk.ukim.finki.emtlabb.service.application.impl;

import mk.ukim.finki.emtlabb.dto.CreateUserDto;
import mk.ukim.finki.emtlabb.dto.DisplayUserDto;
import mk.ukim.finki.emtlabb.dto.LoginResponseDto;
import mk.ukim.finki.emtlabb.dto.LoginUserDto;
import mk.ukim.finki.emtlabb.helpers.JwtHelper;
import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.repository.UserRepository;
import mk.ukim.finki.emtlabb.service.application.UserApplicationService;
import mk.ukim.finki.emtlabb.service.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        return userRepository.findByUsername(loginUserDto.username())
                .filter(user -> passwordEncoder.matches(loginUserDto.password(), user.getPassword()))
                .map(user -> new LoginResponseDto(jwtHelper.generateToken(user)));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}
