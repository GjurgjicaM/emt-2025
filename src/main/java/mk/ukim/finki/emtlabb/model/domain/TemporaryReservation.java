package mk.ukim.finki.emtlabb.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)

    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    public TemporaryReservation(){

    }

    public TemporaryReservation(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }


}
