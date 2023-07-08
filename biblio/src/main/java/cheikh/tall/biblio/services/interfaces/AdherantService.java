package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.adherant.AdherantRequestDto;
import cheikh.tall.biblio.dtos.adherant.AdherantResponseDto;
import cheikh.tall.biblio.entities.Adherant;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public interface AdherantService {
    ResponseEntity<?> addAdherant(AdherantRequestDto adherantRequestDto);
    ResponseEntity<?> updateAdherant(Long id, AdherantRequestDto adherantRequestDto);
    ResponseEntity<?> deleteAdherant(Long id);
    AdherantResponseDto getAdherantById(Long id);
    AdherantResponseDto getAdherantByEmail(String email);
    List<AdherantResponseDto> allAdherants();


    //----------------------------KEYCLOAK PART-------------------------------
    Response addAdherantToKeycloak(AdherantRequestDto adherantRequestDto);
    void updateAdherantToKeycloak(AdherantRequestDto adherantRequestDto);
    void addRoleToAdherant(String email, String roleName);
    void deleteAdherantToKeycloak(Optional<Adherant> adherant);
}
