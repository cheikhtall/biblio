package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.biliothecaire.BibliothecaireRequestDto;
import cheikh.tall.biblio.dtos.biliothecaire.BibliothecarieResponseDto;
import cheikh.tall.biblio.entities.Bibliothecaire;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public interface BibliothecaireService {
    ResponseEntity<?> addBibliothecaire(BibliothecaireRequestDto bibliothecaireRequestDto);
    ResponseEntity<?> updateBibliothecaire(Long id, BibliothecaireRequestDto bibliothecaireRequestDto);
    ResponseEntity<?> deleteBibliothecaire(Long id);
    BibliothecarieResponseDto getBibliothecaireById(Long id);
    List<BibliothecarieResponseDto> allBibliothecaire();

    //----------------------------KEYCLOAK PART-------------------------------

    Response addBibliothecaireToKeycloak(BibliothecaireRequestDto bibliothecaireRequestDto);
    void updateBibliothecaireToKeycloak(BibliothecaireRequestDto bibliothecaireRequestDto);
    void addRoleToBibliothecaire(String email, String roleName);
    void deleteBibliothecaireToKeycloak(Optional<Bibliothecaire> bibliothecaire);
}
