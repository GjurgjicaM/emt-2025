package mk.ukim.finki.emtlabb.web;

        import io.swagger.v3.oas.annotations.Operation;
        import mk.ukim.finki.emtlabb.model.Country;
        import mk.ukim.finki.emtlabb.service.CountryService;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import io.swagger.v3.oas.annotations.tags.Tag;

        import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "Country API")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Returns all countries")
    public List<Country> findAll(){
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a country by its ID")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return countryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new country")
    public ResponseEntity<Country> save(@RequestBody Country category) {
        return countryService.save(category)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates a country record")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a country by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
