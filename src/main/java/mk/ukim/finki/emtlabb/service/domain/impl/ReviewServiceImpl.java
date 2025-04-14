package mk.ukim.finki.emtlabb.service.domain.impl;

import mk.ukim.finki.emtlabb.model.domain.Review;
import mk.ukim.finki.emtlabb.repository.ReviewRepository;
import mk.ukim.finki.emtlabb.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Optional<Review> save(Review review) {
        return Optional.of(reviewRepository.save(review));
    }



    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
