package cheikh.tall.biblio.repositoties;

import cheikh.tall.biblio.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByTitle(String title);
    List<Livre> findByPlusDemande(Boolean plusDemande);
    List<Livre> findByRecent(Boolean recent);
}
