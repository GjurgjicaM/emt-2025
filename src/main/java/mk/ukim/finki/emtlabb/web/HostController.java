package mk.ukim.finki.emtlabb.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    public final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
   @Operation(summary = "Returns all hosts")
    public List<Host> findAll() {
        return this.hostService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a host by its ID")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new host")
    public ResponseEntity<Host> save(@RequestBody Host host) {
        return this.hostService.save(host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates a host record")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody Host host) {
        return this.hostService.update(id, host)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a host by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
