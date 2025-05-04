package mk.ukim.finki.emtlabb.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_per_hosts")
@Immutable
public class AccommodationsPerHostView {
    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Integer getNumAccommodations() {
        return numAccommodations;
    }

    public void setNumAccommodations(Integer numAccommodations) {
        this.numAccommodations = numAccommodations;
    }
}
