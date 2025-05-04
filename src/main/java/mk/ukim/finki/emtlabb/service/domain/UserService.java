package mk.ukim.finki.emtlabb.service.domain;

import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    List<User>getAllUsersWithoutTempReservations();
}
