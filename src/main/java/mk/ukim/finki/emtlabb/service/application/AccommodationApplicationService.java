package mk.ukim.finki.emtlabb.service.application;

import mk.ukim.finki.emtlabb.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlabb.dto.CreateReviewDto;
import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> markAsRented(Long id);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> addReview(Long id,  CreateReviewDto reviewDto);
}
