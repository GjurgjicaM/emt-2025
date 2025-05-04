package mk.ukim.finki.emtlabb.service.domain.impl;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Review;
import mk.ukim.finki.emtlabb.repository.AccommodationRepository;
import mk.ukim.finki.emtlabb.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.emtlabb.service.domain.AccommodationService;
import mk.ukim.finki.emtlabb.service.domain.HostService;
import mk.ukim.finki.emtlabb.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final ReviewService reviewService;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, ReviewService reviewService, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.reviewService = reviewService;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(hostService.findById(accommodation.getHost().getId()).get());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getCategory() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms(), new Review()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accommodationRepository.save(accommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> addReview(Long id, Review review) {
        review = reviewService.save(review).get();
        Accommodation accommodation = this.accommodationRepository.findById(id).get();

        accommodation.getReviewList().add(review);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
    }
}
