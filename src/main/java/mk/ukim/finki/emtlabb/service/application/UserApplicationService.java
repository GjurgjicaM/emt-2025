package mk.ukim.finki.emtlabb.service.application;

import mk.ukim.finki.emtlabb.dto.CreateUserDto;
import mk.ukim.finki.emtlabb.dto.DisplayUserDto;
import mk.ukim.finki.emtlabb.dto.LoginResponseDto;
import mk.ukim.finki.emtlabb.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
