package cheikh.tall.biblio.repositoties;

import cheikh.tall.biblio.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
