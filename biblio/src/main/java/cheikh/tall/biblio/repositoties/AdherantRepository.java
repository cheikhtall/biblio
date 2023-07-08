package cheikh.tall.biblio.repositoties;

import cheikh.tall.biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherantRepository extends JpaRepository<Adherant, Long> {
    Boolean existsByEmail(String email);
    Adherant findByEmail(String email);
}
