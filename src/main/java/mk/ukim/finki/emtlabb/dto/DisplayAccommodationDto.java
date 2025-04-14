package mk.ukim.finki.emtlabb.dto;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.domain.Review;
import mk.ukim.finki.emtlabb.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        boolean isRented,

        List<DisplayReviewDto>reviewList,
        double avgRating
) {

    public static DisplayAccommodationDto from(Accommodation accommodation){
        double average = accommodation.getReviewList().isEmpty() ? 0.0 :
                accommodation.getReviewList().stream().mapToDouble(Review::getRate).average().orElse(0.0);

        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.isRented(),
                accommodation.getReviewList().stream().map(DisplayReviewDto::from).collect(Collectors.toList()),
                average
        );
    }
    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host hostId){
        return new Accommodation(name,category,hostId,numRooms);
    }
}
