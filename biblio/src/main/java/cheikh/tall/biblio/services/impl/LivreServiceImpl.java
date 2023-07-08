package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.dtos.livre.LivreRequestDto;
import cheikh.tall.biblio.dtos.livre.LivreResponseDto;
import cheikh.tall.biblio.entities.Livre;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.LivreMapper;
import cheikh.tall.biblio.repositoties.CategorieRepository;
import cheikh.tall.biblio.repositoties.LivreRepository;
import cheikh.tall.biblio.services.interfaces.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivreServiceImpl implements LivreService {

    private LivreMapper livreMapper;
    private LivreRepository livreRepository;
    private CategorieRepository categorieRepository;

    public LivreServiceImpl(LivreMapper livreMapper, LivreRepository livreRepository, CategorieRepository categorieRepository) {
        this.livreMapper = livreMapper;
        this.livreRepository = livreRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public ResponseEntity<?> addLivre(LivreRequestDto livreRequestDto) {
        Livre livre = livreMapper.livreRequestDtoToLivre(livreRequestDto);
        livre.setRecent(true);
        livreRepository.save(livre);
        return ResponseEntity.ok("Livre ajouté avec success");
    }

    @Override
    public ResponseEntity<?> updateLivre(Long id, LivreRequestDto livreRequestDto) {
        Livre l = livreRepository.findById(id).orElseThrow(
                ()-> new CustomException("Ce livre est introuvable")
        );
        Livre livre = livreMapper.livreRequestDtoToLivre(livreRequestDto);
        livre.setId(l.getId());
        livreRepository.save(livre);
        return ResponseEntity.ok("Modification enregistrée");
    }

    @Override
    public ResponseEntity<?> deleteLivre(Long id) {
        Livre livre = livreRepository.findById(id).orElseThrow(
                ()-> new CustomException("Ce livre est introuvable")
        );
        livre.getCategories().forEach(
                categorie -> {
                    List<Livre> updatedLivres = categorie.getLivres()
                            .stream().filter(livre1 -> !livre1.equals(livre))
                            .collect(Collectors.toList());
                    categorie.setLivres(updatedLivres);
                    categorieRepository.save(categorie);
                }
        );
        livre.setCategories(null);
        livreRepository.delete(livre);
        return ResponseEntity.ok("Suppression réussie");
    }

    @Override
    public LivreResponseDto getLivreById(Long id) {
        Livre livre = livreRepository.findById(id).orElseThrow(
            ()-> new CustomException("Ce livre est introuvable")
    );
        return livreMapper.livreToLivreResponseDto(livre);
    }

    @Override
    public List<LivreResponseDto> allLivres() {
        List<Livre> livres = livreRepository.findAll();
        List<LivreResponseDto> livreResponseDtos = livres.stream().map(
                livre -> livreMapper.livreToLivreResponseDto(livre)
        ).collect(Collectors.toList());
        return livreResponseDtos;
    }

    @Override
    public List<LivreResponseDto> allLivresByTitle(String title) {
        List<Livre> livres = livreRepository.findByTitle(title);
        List<LivreResponseDto> livreResponseDtos = livres.stream().map(
                livre -> livreMapper.livreToLivreResponseDto(livre)
        ).collect(Collectors.toList());
        return livreResponseDtos;
    }

    @Override
    public List<LivreResponseDto> allLivresByPlusDemande(Boolean plusDemande) {
        List<Livre> livres = livreRepository.findByPlusDemande(plusDemande);
        List<LivreResponseDto> livreResponseDtos = livres.stream().map(
                livre -> livreMapper.livreToLivreResponseDto(livre)
        ).collect(Collectors.toList());
        return livreResponseDtos;
    }

    @Override
    public List<LivreResponseDto> allLivresByRecent(Boolean recent) {
        List<Livre> livres = livreRepository.findByRecent(recent);
        List<LivreResponseDto> livreResponseDtos = livres.stream().map(
                livre -> livreMapper.livreToLivreResponseDto(livre)
        ).collect(Collectors.toList());
        return livreResponseDtos;
    }
}
