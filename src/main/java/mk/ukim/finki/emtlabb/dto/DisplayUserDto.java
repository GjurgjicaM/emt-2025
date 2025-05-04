package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.enumerations.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record DisplayUserDto(String username, String name, String surname, Role role,    List<Long> temporaryReservationIds) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getTemporaryReservations() != null
                        ? user.getTemporaryReservations().stream()
                        .flatMap(tr -> tr.getAccommodations() != null ? tr.getAccommodations().stream() : Stream.empty())
                        .map(Accommodation::getId)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }

}
