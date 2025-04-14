package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;

import java.util.List;

public record TemporaryReservationDto(
        Long id,
        DisplayUserDto user,
        List<DisplayAccommodationDto>accommodations
) {
    public static TemporaryReservationDto from(TemporaryReservation temporaryReservation){
        return new TemporaryReservationDto(
                temporaryReservation.getId(),
                DisplayUserDto.from(temporaryReservation.getUser()),
                DisplayAccommodationDto.from(temporaryReservation.getAccommodations())
        );
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public DisplayUserDto user() {
        return user;
    }

    @Override
    public List<DisplayAccommodationDto> accommodations() {
        return accommodations;
    }
}
