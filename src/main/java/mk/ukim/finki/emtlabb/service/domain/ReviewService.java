package mk.ukim.finki.emtlabb.service.domain;

import mk.ukim.finki.emtlabb.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();

    Optional<Review> save(Review review);

    void deleteById(Long id);

}
