package cheikh.tall.biblio.repositoties;

import cheikh.tall.biblio.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Boolean existsByName(String name);
}
