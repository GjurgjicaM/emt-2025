package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
