package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.livre.LivreRequestDto;
import cheikh.tall.biblio.dtos.livre.LivreResponseDto;
import cheikh.tall.biblio.services.interfaces.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class LivreController {
    private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }
    @GetMapping(path = "/livres")
    public List<LivreResponseDto> allLivres(){
        return livreService.allLivres();
    }
    @GetMapping(path = "/livres/{title}")
    public List<LivreResponseDto> allLivresByTitle(@PathVariable String title){
        return livreService.allLivresByTitle(title);
    }
    @GetMapping(path = "/livresPlusDemandes/{plusDemande}")
    public List<LivreResponseDto> allLivresByPlusDemandes(@PathVariable Boolean plusDemande){
        return livreService.allLivresByPlusDemande(plusDemande);
    }
    @GetMapping(path = "/livresRecents/{recent}")
    public List<LivreResponseDto> allLivresByRecent(@PathVariable Boolean recent){
        return livreService.allLivresByRecent(recent);
    }
    @GetMapping(path = "/livre/{id}")
    public LivreResponseDto getLivreById(@PathVariable Long id){
        return livreService.getLivreById(id);
    }
    @PostMapping(path = "/livre")
    public ResponseEntity<?> addLivre(@RequestBody LivreRequestDto livreRequestDto){
        return livreService.addLivre(livreRequestDto);
    }
    @PutMapping(path = "/livre/{id}")
    public ResponseEntity<?> updateLivre(@PathVariable Long id, @RequestBody LivreRequestDto livreRequestDto){
        return livreService.updateLivre(id, livreRequestDto);
    }
    @DeleteMapping(path = "/livre/{id}")
    public ResponseEntity<?> deleteLivre(@PathVariable Long id){
        return  livreService.deleteLivre(id);
    }
}
