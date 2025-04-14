package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms

) {

    public static CreateAccommodationDto from(Accommodation accommodation){
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host hostId){
        return new Accommodation(name,category,hostId,numRooms);
    }
}
