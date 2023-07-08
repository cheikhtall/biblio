package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.adherant.AdherantRequestDto;
import cheikh.tall.biblio.dtos.adherant.AdherantResponseDto;
import cheikh.tall.biblio.entities.Adherant;
import cheikh.tall.biblio.services.interfaces.AdherantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdherantController {
    private AdherantService adherantService;

    public AdherantController(AdherantService adherantService) {
        this.adherantService = adherantService;
    }

    @GetMapping(path = "/adherants")
    public List<AdherantResponseDto> allAdherants(){
        return adherantService.allAdherants();
    }
    @GetMapping(path = "/adherant/{id}")
    public AdherantResponseDto getAdherantById(@PathVariable Long id){
        return adherantService.getAdherantById(id);
    }
    @GetMapping(path = "/adherantByEmail/{email}")
    public AdherantResponseDto getAdherantByEmail(@PathVariable String email){
        return adherantService.getAdherantByEmail(email);
    }
    @PostMapping(path = "/adherant")
    public ResponseEntity<?> addAdherant(@RequestBody AdherantRequestDto adherantRequestDto){
        adherantService.addAdherantToKeycloak(adherantRequestDto);
        adherantService.addRoleToAdherant(adherantRequestDto.getEmail(), "APP_ADHERANT");
        return adherantService.addAdherant(adherantRequestDto);
    }
    @PutMapping(path = "/adherant/{id}")
    public ResponseEntity<?> updateAdherant(@PathVariable Long id, @RequestBody AdherantRequestDto adherantRequestDto){
        adherantService.updateAdherantToKeycloak(adherantRequestDto);
        return adherantService.updateAdherant(id, adherantRequestDto);
    }
    @DeleteMapping(path = "/adherant/{id}")
    public ResponseEntity<?> deleteAdherant(@PathVariable Long id, @RequestBody Optional<Adherant> adherant){
        adherantService.deleteAdherantToKeycloak(adherant);
        return adherantService.deleteAdherant(id);
    }
}
