package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.pret.PretRequestDto;
import cheikh.tall.biblio.dtos.pret.PretResponseDto;
import cheikh.tall.biblio.services.interfaces.PretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PretController {
    private PretService pretService;
    public PretController(PretService pretService) {
        this.pretService = pretService;
    }
    @GetMapping(path = "/prets")
    public List<PretResponseDto> allPrets(){
        return pretService.allPrets();
    }
    @GetMapping(path = "/pret/{id}")
    public PretResponseDto getPretById(@PathVariable Long id){
        return pretService.getPretById(id);
    }
    @PostMapping(path = "/pret")
    public ResponseEntity<?> addPret(@RequestBody PretRequestDto pretRequestDto){
        return pretService.addPret(pretRequestDto);
    }
    @PutMapping(path = "/pret/{id}")
    public ResponseEntity<?> updatePret(@PathVariable Long id, @RequestBody PretRequestDto pretRequestDto){
        return pretService.updatePret(id, pretRequestDto);
    }
    @DeleteMapping("/pret/{id}")
    public ResponseEntity<?> deletePret(@PathVariable Long id){
        return pretService.deletePret(id);
    }
}
