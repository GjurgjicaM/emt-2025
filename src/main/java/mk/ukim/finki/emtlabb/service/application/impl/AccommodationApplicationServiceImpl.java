package mk.ukim.finki.emtlabb.service.application.impl;

import mk.ukim.finki.emtlabb.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlabb.dto.CreateReviewDto;
import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.domain.Review;
import mk.ukim.finki.emtlabb.model.enumerations.Category;
import mk.ukim.finki.emtlabb.model.exceptions.HostNotFound;
import mk.ukim.finki.emtlabb.service.application.AccommodationApplicationService;
import mk.ukim.finki.emtlabb.service.domain.AccommodationService;
import mk.ukim.finki.emtlabb.service.domain.HostService;
import mk.ukim.finki.emtlabb.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final ReviewService reviewService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, ReviewService reviewService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.reviewService = reviewService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());
        return accommodationService.update(id,
                accommodation.toAccommodation(
                        host.orElse(null)
                )
        ).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());

        if (host.isPresent()) {
            return accommodationService.save(accommodation.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accommodationService.save(accommodation)
                    .map(DisplayAccommodationDto::from)
                    .orElse(null);
        });
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> addReview(Long id, CreateReviewDto reviewDto) {
        return accommodationService.findById(id).map(accommodation -> {
            Review review = reviewDto.toReview();

            review = reviewService.save(review).orElseThrow(() -> new RuntimeException("Review not saved"));

            accommodation.getReviewList().add(review);

            return accommodationService.update(id, accommodation)
                    .map(DisplayAccommodationDto::from)
                    .orElse(null);
        });
    }

    @Override
    public List<DisplayAccommodationDto> search(String name, Category category, Long hostId, Integer numRooms) {
        Host host = (hostId != null) ? hostService.findById(hostId).orElseThrow(HostNotFound::new) : null;

        return accommodationService.findAll().stream()
                .filter(a -> name == null || a.getName().contains(name))
                .filter(a -> category == null || a.getCategory().equals(category))
                .filter(a -> host == null || a.getHost().equals(host))
                .filter(a -> numRooms == null || a.getNumRooms().equals(numRooms))
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }
}