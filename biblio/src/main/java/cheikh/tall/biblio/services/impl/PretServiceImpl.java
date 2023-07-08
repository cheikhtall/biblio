package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.dtos.pret.PretRequestDto;
import cheikh.tall.biblio.dtos.pret.PretResponseDto;
import cheikh.tall.biblio.entities.Bibliothecaire;
import cheikh.tall.biblio.entities.Pret;
import cheikh.tall.biblio.enums.EtatPret;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.PretMapper;
import cheikh.tall.biblio.repositoties.BibliothecaireRepository;
import cheikh.tall.biblio.repositoties.PretRepository;
import cheikh.tall.biblio.services.interfaces.PretService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PretServiceImpl implements PretService {

    private PretRepository pretRepository;
    private PretMapper pretMapper;
    private BibliothecaireRepository bibliothecaireRepository;

    public PretServiceImpl(PretRepository pretRepository, PretMapper pretMapper,
                           BibliothecaireRepository bibliothecaireRepository) {
        this.pretRepository = pretRepository;
        this.pretMapper = pretMapper;
        this.bibliothecaireRepository = bibliothecaireRepository;
    }

    @Override
    public ResponseEntity<?> addPret(PretRequestDto pretRequestDto) {
        Pret pret = pretMapper.pretRsquestDtoToPret(pretRequestDto);
        pretRepository.save(pret);
        return ResponseEntity.ok("Votre prêt est enregistré");
    }

    @Override
    public ResponseEntity<?> updatePret(Long id, PretRequestDto pretRequestDto) {
        Pret p = pretRepository.findById(id).orElseThrow(
                ()-> new CustomException("Pret introuvable")
        );
        Pret pret = pretMapper.pretRsquestDtoToPret(pretRequestDto);
        pret.setId(p.getId());
        pretRepository.save(pret);
        return ResponseEntity.ok("Votre prêt est enregistré");
    }

    @Override
    public ResponseEntity<?> deletePret(Long id) {
        Pret p = pretRepository.findById(id).orElseThrow(
                ()-> new CustomException("Pret introuvable")
        );
        pretRepository.delete(p);
        return ResponseEntity.ok("Suppression réussie");
    }

    @Override
    public PretResponseDto getPretById(Long id) {
        Pret pret = pretRepository.findById(id).orElseThrow(
                ()-> new CustomException("Etat du pret modifié avec succes")
        );
        return pretMapper.pretToPretResonseDto(pret);
    }

    @Override
    public List<PretResponseDto> allPrets() {
        List<Pret> prets = pretRepository.findAll();
        List<PretResponseDto> pretResponseDtos = prets.stream().map(
                pret -> pretMapper.pretToPretResonseDto(pret)
        ).collect(Collectors.toList());
        return pretResponseDtos;
    }

    @Override
    public ResponseEntity<?> validerPret(Long idPret, Long idBibio) {
        Pret pret =pretRepository.findById(idPret).orElseThrow(
                ()-> new CustomException("Pret introuvable"));
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(idBibio)
                .orElseThrow(
                        ()-> new CustomException("bibliothecaire introuvable"));
        pret.setEtatPret(EtatPret.VALIDE);
        pret.setBibliothecaire(bibliothecaire);
        return ResponseEntity.ok("Ce prêt vient d'être validé");
    }

    @Override
    public ResponseEntity<?> refuserPret(Long idPret, Long idBibio) {
        Pret pret =pretRepository.findById(idPret).orElseThrow(
                ()-> new CustomException("Pret introuvable"));
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(idBibio)
                .orElseThrow(
                        ()-> new CustomException("bibliothecaire introuvable"));
        pret.setEtatPret(EtatPret.REFUSE);
        pret.setBibliothecaire(bibliothecaire);
        return ResponseEntity.ok("Ce prêt vient d'être refusé");
    }
}
