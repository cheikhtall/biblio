package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.categorie.CategorieRequestDto;
import cheikh.tall.biblio.dtos.categorie.CategorieResponseDto;
import cheikh.tall.biblio.services.interfaces.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping(path = "/categories")
    public List<CategorieResponseDto> allCategories(){
        return  categorieService.allCategories();
    }
    @GetMapping(path = "/categorie/{id}")
    public CategorieResponseDto getCategorieById(@PathVariable Long id){
        return categorieService.getCategorieById(id);
    }
    @PostMapping(path = "/categorie")
    public ResponseEntity<?> addCategorie(@RequestBody CategorieRequestDto categorieRequestDto){
        return categorieService.addCategorie(categorieRequestDto);
    }
    @PutMapping(path = "/categorie/{id}")
    public ResponseEntity<?> updateCategorie(@PathVariable Long id, @RequestBody CategorieRequestDto categorieRequestDto){
        return categorieService.updateCategorie(id, categorieRequestDto);
    }
    @DeleteMapping(path = "/categorie/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable Long id){
        return categorieService.deleteCategorie(id);
    }
}
