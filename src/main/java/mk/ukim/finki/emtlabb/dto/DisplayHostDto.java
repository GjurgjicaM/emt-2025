package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Country;
import mk.ukim.finki.emtlabb.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        Long country
) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String surname() {
        return surname;
    }

    @Override
    public Long country() {
        return country;
    }
}
