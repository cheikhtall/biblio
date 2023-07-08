package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.entities.Categorie;
import cheikh.tall.biblio.entities.Livre;
import cheikh.tall.biblio.repositoties.CategorieRepository;
import cheikh.tall.biblio.repositoties.LivreRepository;
import cheikh.tall.biblio.services.interfaces.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GeneralServiceImpl implements GeneralService {

    private LivreRepository livreRepository;
    private CategorieRepository categorieRepository;

    public GeneralServiceImpl(LivreRepository livreRepository, CategorieRepository categorieRepository) {
        this.livreRepository = livreRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public ResponseEntity<?> addLivreToCategorie(Long idLivre, Long idCategorie) {
        Livre livre = livreRepository.findById(idLivre).get();
        Categorie categorie = categorieRepository.findById(idCategorie).get();
        livre.getCategories().add(categorie);
        categorie.getLivres().add(livre);
        return ResponseEntity.ok("Ajouté");
    }
}
