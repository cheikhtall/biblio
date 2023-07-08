package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.categorie.CategorieRequestDto;
import cheikh.tall.biblio.dtos.categorie.CategorieResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategorieService {
    ResponseEntity<?> addCategorie(CategorieRequestDto categorieRequestDto);
    ResponseEntity<?> updateCategorie(Long id, CategorieRequestDto categorieRequestDto);
    ResponseEntity<?> deleteCategorie(Long id);
    CategorieResponseDto getCategorieById(Long id);
    List<CategorieResponseDto> allCategories();
}
