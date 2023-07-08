package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.biliothecaire.BibliothecaireRequestDto;
import cheikh.tall.biblio.dtos.biliothecaire.BibliothecarieResponseDto;
import cheikh.tall.biblio.entities.Bibliothecaire;
import cheikh.tall.biblio.services.interfaces.BibliothecaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class BibliothecaireContoller {
    private BibliothecaireService bibliothecaireService;

    public BibliothecaireContoller(BibliothecaireService bibliothecaireService) {
        this.bibliothecaireService = bibliothecaireService;
    }
    @GetMapping(path = "/bibliothecaires")
    public List<BibliothecarieResponseDto> allBibliothecaires(){
        return bibliothecaireService.allBibliothecaire();
    }
    @GetMapping(path = "/bibliothecaire/{id}")
    public BibliothecarieResponseDto getBiblioById(@PathVariable Long id){
        return bibliothecaireService.getBibliothecaireById(id);
    }
    @PostMapping(path = "/bibliothecaire")
    public ResponseEntity<?> addBibliothecaire(@RequestBody BibliothecaireRequestDto bibliothecaireRequestDto){
        bibliothecaireService.addBibliothecaireToKeycloak(bibliothecaireRequestDto);
        bibliothecaireService.addRoleToBibliothecaire(bibliothecaireRequestDto.getEmail(), "APP_BIBLIO");
        return bibliothecaireService.addBibliothecaire(bibliothecaireRequestDto);
    }
    @PutMapping(path = "/bibliothecaire/{id}")
    public ResponseEntity<?> updateBibliothecaire(@PathVariable Long id, @RequestBody BibliothecaireRequestDto bibliothecaireRequestDto){
        bibliothecaireService.updateBibliothecaireToKeycloak(bibliothecaireRequestDto);
        return bibliothecaireService.updateBibliothecaire(id, bibliothecaireRequestDto);
    }
    @DeleteMapping(path = "/bibliothecaire/{id}")
    public ResponseEntity<?> deleteBibliothecaire(@PathVariable Long id, @RequestBody Optional<Bibliothecaire> bibliothecaire){
        bibliothecaireService.deleteBibliothecaireToKeycloak(bibliothecaire);
        return bibliothecaireService.deleteBibliothecaire(id);
    }
}
