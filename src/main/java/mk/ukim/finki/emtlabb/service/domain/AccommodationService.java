package mk.ukim.finki.emtlabb.service.domain;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> markAsRented(Long id);

    void deleteById(Long id);

    Optional<Accommodation>addReview(Long id, Review review);



}
