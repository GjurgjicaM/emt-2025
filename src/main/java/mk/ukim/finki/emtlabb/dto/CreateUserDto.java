package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.enumerations.Role;

public record CreateUserDto (
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role

){
    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}
