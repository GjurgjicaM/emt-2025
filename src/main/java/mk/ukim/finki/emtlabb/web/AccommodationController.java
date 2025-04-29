package mk.ukim.finki.emtlabb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlabb.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlabb.dto.CreateReviewDto;
import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.model.enumerations.Category;
import mk.ukim.finki.emtlabb.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Accommodation API")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }


    @GetMapping
    @Operation(summary = "Returns all accommodations")
    public List<DisplayAccommodationDto> findAll() {
        return accommodationApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns accommodation by ID")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return this.accommodationApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new accommodation")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.save(accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates an accommodation record")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Marks an accommodation as rented")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id) {
        return accommodationApplicationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes an accommodation by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add-review/{id}")
    @Operation(summary = "Adds an accommodation review")
    public Optional<DisplayAccommodationDto> addReview(@PathVariable Long id, CreateReviewDto reviewDto) {
        return accommodationApplicationService.addReview(id, reviewDto);
    }

    @GetMapping("/search")
    @Operation(summary = "Search accommodations by name, category, hostId or number of rooms")
    public List<DisplayAccommodationDto> searchAccommodations(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Integer numRooms
    ) {
        return accommodationApplicationService.search(name, category, hostId, numRooms);
    }
}
