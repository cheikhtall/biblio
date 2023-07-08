package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.services.interfaces.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class GeneralController {

    private GeneralService generalService;

    public GeneralController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @PostMapping("/addlivretocategorie")
    public ResponseEntity<?> addLivreToCategorie(@RequestParam Long idLivre, @RequestParam Long idCategorie){
        return generalService.addLivreToCategorie(idLivre, idCategorie);
    }
}
