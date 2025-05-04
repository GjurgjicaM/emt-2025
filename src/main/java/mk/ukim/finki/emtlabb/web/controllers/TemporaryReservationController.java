package mk.ukim.finki.emtlabb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.dto.TemporaryReservationDto;
import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.service.application.TemporaryReservationApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temporary-reservations")
@Tag(name = "Temporary Reservations API", description = "Endpoints for managing the temporary reservations")
public class TemporaryReservationController {
    private final TemporaryReservationApplicationService temporaryReservationApplicationService;

    public TemporaryReservationController(TemporaryReservationApplicationService temporaryReservationApplicationService) {
        this.temporaryReservationApplicationService = temporaryReservationApplicationService;
    }


    @Operation(summary = "Returns all accommodations added in Temporary Reservation")
    @GetMapping("/accommodations")
    public List<DisplayAccommodationDto>getAllAccommodationsInTempRes(Authentication authentication){
        String username = authentication.getName();
        return temporaryReservationApplicationService.listAllAccommodationsInTemporaryReservation(username);
    }

    @Operation(
            summary = "Add accommodation to temporary reservation",
            description = "Adds an accommodation"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to temporary reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Accommodation not available"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<TemporaryReservationDto>addAccommodationToTempRes(Authentication authentication, @PathVariable Long id){
        try{
            User user = (User) authentication.getPrincipal();
            return temporaryReservationApplicationService.addAccommodation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Confirms temporary reservation")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Temporary reservation confirmed"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )}
    )
    @PostMapping("/confirm")
    public ResponseEntity<TemporaryReservationDto>confirmReservation(Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            return temporaryReservationApplicationService.confirmReservation(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
