package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.dtos.categorie.CategorieRequestDto;
import cheikh.tall.biblio.dtos.categorie.CategorieResponseDto;
import cheikh.tall.biblio.entities.Categorie;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.CategorieMapper;
import cheikh.tall.biblio.repositoties.CategorieRepository;
import cheikh.tall.biblio.services.interfaces.CategorieService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private CategorieMapper categorieMapper;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public ResponseEntity<?> addCategorie(CategorieRequestDto categorieRequestDto) {
        if (categorieRepository.existsByName(categorieRequestDto.getName())){
            return new ResponseEntity<>("Cette catégorie existe deja", HttpStatusCode.valueOf(500));
        }
        Categorie categorie = categorieMapper.categorieRequestDtoToCategorie(categorieRequestDto);
        categorieRepository.save(categorie);
        return ResponseEntity.ok("Une nouvelle catégorie de livre vient d'être créé!");
    }

    @Override
    public ResponseEntity<?> updateCategorie(Long id, CategorieRequestDto categorieRequestDto) {
        Categorie c = categorieRepository.findById(id).orElseThrow(
                ()-> new CustomException("Cette catégorie de livre n'existe pas")
        );
        Categorie categorie = categorieMapper.categorieRequestDtoToCategorie(categorieRequestDto);
        categorie.setId(c.getId());
        categorieRepository.save(categorie);
        return ResponseEntity.ok("Modification enregitrée!");
    }

    @Override
    public ResponseEntity<?> deleteCategorie(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(
                ()-> new CustomException("Cette catégorie de livre n'existe pas")
        );
        categorieRepository.delete(categorie);
        return ResponseEntity.ok("Suppression réussie!");
    }

    @Override
    public CategorieResponseDto getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(
                ()-> new CustomException("Cette catégorie de livre n'existe pas")
        );
        return categorieMapper.categorieToCategorieResponseDto(categorie);
    }

    @Override
    public List<CategorieResponseDto> allCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        List<CategorieResponseDto> categorieResponseDtos = categories.stream().map(
                categorie -> categorieMapper.categorieToCategorieResponseDto(categorie)
        ).collect(Collectors.toList());
        return categorieResponseDtos;
    }
}
