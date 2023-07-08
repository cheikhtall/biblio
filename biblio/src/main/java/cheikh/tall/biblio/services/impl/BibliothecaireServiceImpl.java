package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.config.KeycloakConfig;
import cheikh.tall.biblio.dtos.biliothecaire.BibliothecaireRequestDto;
import cheikh.tall.biblio.dtos.biliothecaire.BibliothecarieResponseDto;
import cheikh.tall.biblio.entities.Bibliothecaire;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.BiblilothecaireMapper;
import cheikh.tall.biblio.repositoties.BibliothecaireRepository;
import cheikh.tall.biblio.services.interfaces.BibliothecaireService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BibliothecaireServiceImpl implements BibliothecaireService {

    private BiblilothecaireMapper biblilothecaireMapper;
    private BibliothecaireRepository bibliothecaireRepository;
    public KeycloakConfig kc=new KeycloakConfig();
    public Keycloak keycloak=kc.getInstance();

    public BibliothecaireServiceImpl(BiblilothecaireMapper biblilothecaireMapper, BibliothecaireRepository bibliothecaireRepository) {
        this.biblilothecaireMapper = biblilothecaireMapper;
        this.bibliothecaireRepository = bibliothecaireRepository;
    }

    @Override
    public ResponseEntity<?> addBibliothecaire(BibliothecaireRequestDto bibliothecaireRequestDto) {
        Bibliothecaire bibliothecaire = biblilothecaireMapper.biblioRquestToBibliothecaire(bibliothecaireRequestDto);
        bibliothecaireRepository.save(bibliothecaire);
        return ResponseEntity.ok("Un nouveau bibliothecaire vient d'être créé!");
    }

    @Override
    public ResponseEntity<?> updateBibliothecaire(Long id, BibliothecaireRequestDto bibliothecaireRequestDto) {
        Bibliothecaire b = bibliothecaireRepository.findById(id).orElseThrow(
                () -> new CustomException("Bibliothecaire introuvable")
        );
        Bibliothecaire bibliothecaire = biblilothecaireMapper.biblioRquestToBibliothecaire(bibliothecaireRequestDto);
        bibliothecaire.setId(b.getId());
        bibliothecaireRepository.save(bibliothecaire);
        return ResponseEntity.ok("Modification enregistrée!");
    }

    @Override
    public ResponseEntity<?> deleteBibliothecaire(Long id) {
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(id).orElseThrow(
                () -> new CustomException("Bibliothecaire introuvable")
        );
        bibliothecaireRepository.delete(bibliothecaire);
        return ResponseEntity.ok("Suppression réussie!");
    }
    @Override
    public BibliothecarieResponseDto getBibliothecaireById(Long id) {
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(id).orElseThrow(
                () -> new CustomException("Bibliothecaire introuvable")
        );
        return biblilothecaireMapper.bliblioToBlioResponseDto(bibliothecaire);
    }

    @Override
    public List<BibliothecarieResponseDto> allBibliothecaire() {
        List<Bibliothecaire> bibliothecaires = bibliothecaireRepository.findAll();
        List<BibliothecarieResponseDto> bibliothecarieResponseDtos = bibliothecaires.stream().map(
                bibliothecaire -> biblilothecaireMapper.bliblioToBlioResponseDto(bibliothecaire)
        ).collect(Collectors.toList());
        return bibliothecarieResponseDtos;
    }


    //----------------------------KEYCLOAK PART-------------------------------
    @Override
    public Response addBibliothecaireToKeycloak(BibliothecaireRequestDto bibliothecaireRequestDto) {
        CredentialRepresentation credential = kc.createCredential(bibliothecaireRequestDto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(bibliothecaireRequestDto.getEmail());
        user.setEmail(bibliothecaireRequestDto.getEmail());
        user.setFirstName(bibliothecaireRequestDto.getFirstname());
        user.setLastName(bibliothecaireRequestDto.getLastName());
        user.setCredentials(Arrays.asList(credential));
        user.setEnabled(true);

        return keycloak.realms().realm("biblio").users().create(user);
    }

    @Override
    public void updateBibliothecaireToKeycloak(BibliothecaireRequestDto bibliothecaireRequestDto) {
        UserRepresentation user=keycloak.realm("biblio")
                .users().search(bibliothecaireRequestDto.getEmail()).get(0);
        user.setEmail(bibliothecaireRequestDto.getEmail());
        user.setUsername(bibliothecaireRequestDto.getEmail());
        user.setFirstName(bibliothecaireRequestDto.getFirstname());
        user.setLastName(bibliothecaireRequestDto.getLastName());
        addRoleToBibliothecaire(user.getUsername(), "APP_BIBLIO");
        keycloak.realm("biblio").users().get(user.getId()).update(user);
    }

    @Override
    public void addRoleToBibliothecaire(String email, String roleName) {
        List<RoleRepresentation> roles=keycloak.realm("biblio").roles().list();
        UsersResource users=keycloak.realm("biblio").users();
        UserRepresentation user=users.search(email).get(0);
        RoleRepresentation role=new RoleRepresentation();

        for(RoleRepresentation r:roles){
            if(r.getName().equals(roleName)){
                role=r;
            }
        }
        users.get(user.getId()).roles().realmLevel().add(List.of(role));
    }

    @Override
    public void deleteBibliothecaireToKeycloak(Optional<Bibliothecaire> bibliothecaire) {
        UserRepresentation user=keycloak.realm("biblio")
                .users().search(bibliothecaire.get().getEmail()).get(0);
        user.setEmail(bibliothecaire.get().getEmail());
        user.setUsername(bibliothecaire.get().getEmail());
        user.setFirstName(bibliothecaire.get().getFirstname());
        keycloak.realm("biblio").users().get(user.getId()).remove();
    }
}
