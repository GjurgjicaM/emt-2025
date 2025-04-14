package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toCountry(){
        return new Country(name,continent);
    }
}
