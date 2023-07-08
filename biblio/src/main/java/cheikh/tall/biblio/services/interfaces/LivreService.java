package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.livre.LivreRequestDto;
import cheikh.tall.biblio.dtos.livre.LivreResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LivreService {
    ResponseEntity<?> addLivre(LivreRequestDto livreRequestDto);
    ResponseEntity<?> updateLivre(Long id, LivreRequestDto livreRequestDto);
    ResponseEntity<?> deleteLivre(Long id);
    LivreResponseDto getLivreById(Long id);
    List<LivreResponseDto> allLivres();
    List<LivreResponseDto> allLivresByTitle(String title);
    List<LivreResponseDto> allLivresByPlusDemande(Boolean plusDemande);
    List<LivreResponseDto> allLivresByRecent(Boolean recent);
}
