package cheikh.tall.biblio.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface GeneralService {
    ResponseEntity<?> addLivreToCategorie(Long idLivre, Long idCategorie);
}
