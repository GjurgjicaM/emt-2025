package mk.ukim.finki.emtlabb.web.controllers;

        import io.swagger.v3.oas.annotations.Operation;
        import mk.ukim.finki.emtlabb.dto.CreateCountryDto;
        import mk.ukim.finki.emtlabb.dto.DisplayCountryDto;
        import mk.ukim.finki.emtlabb.model.domain.Country;
        import mk.ukim.finki.emtlabb.service.application.CountryApplicationService;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import io.swagger.v3.oas.annotations.tags.Tag;

        import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "Country API")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }


    @GetMapping
    @Operation(summary = "Returns all countries")
    public List<DisplayCountryDto> findAll(){
        return this.countryApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a country by its ID")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new country")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto countryDto) {
        return countryApplicationService.save(countryDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates a country record")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto countryDto) {
        return countryApplicationService.update(id, countryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a country by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
