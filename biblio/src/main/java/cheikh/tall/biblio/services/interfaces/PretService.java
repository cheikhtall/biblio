package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.pret.PretRequestDto;
import cheikh.tall.biblio.dtos.pret.PretResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PretService {
    ResponseEntity<?> addPret(PretRequestDto pretRequestDto);
    ResponseEntity<?> updatePret(Long id, PretRequestDto pretRequestDto);
    ResponseEntity<?> deletePret(Long id);
    PretResponseDto getPretById(Long id);
    List<PretResponseDto> allPrets();
    ResponseEntity<?> validerPret(Long idPret, Long idBibio);
    ResponseEntity<?> refuserPret(Long idPret, Long idBibio);
}
