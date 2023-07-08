package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.config.KeycloakConfig;
import cheikh.tall.biblio.dtos.adherant.AdherantRequestDto;
import cheikh.tall.biblio.dtos.adherant.AdherantResponseDto;
import cheikh.tall.biblio.entities.Adherant;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.AdherantMapper;
import cheikh.tall.biblio.repositoties.AdherantRepository;
import cheikh.tall.biblio.services.interfaces.AdherantService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatusCode;
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
public class AdherantServiceImpl implements AdherantService {
    private AdherantRepository adherantRepository;
    private AdherantMapper adherantMapper;
    public KeycloakConfig kc=new KeycloakConfig();
    public Keycloak keycloak=kc.getInstance();

    public AdherantServiceImpl(AdherantRepository adherantRepository,
                               AdherantMapper adherantMapper) {
        this.adherantRepository = adherantRepository;
        this.adherantMapper = adherantMapper;
    }

    @Override
    public ResponseEntity<?> addAdherant(AdherantRequestDto adherantRequestDto) {
        if (adherantRepository.existsByEmail(adherantRequestDto.getEmail())){
            return new ResponseEntity<>("Cet email est déjà utilisé", HttpStatusCode.valueOf(500));
        }
        Adherant adherant = adherantMapper.adherantRequestDtoToAdherant(adherantRequestDto);
        adherantRepository.save(adherant);
        return ResponseEntity.ok("Un nouvel adherant vient d'être créé!");
    }

    @Override
    public ResponseEntity<?> updateAdherant(Long id, AdherantRequestDto adherantRequestDto) {
        Adherant a = adherantRepository.findById(id).orElseThrow(
                ()-> new CustomException("Element introuvable!")
        );
        Adherant adherant = adherantMapper.adherantRequestDtoToAdherant(adherantRequestDto);
        adherant.setId(a.getId());
        adherantRepository.save(adherant);
        return ResponseEntity.ok("Modification enregistrée!");
    }

    @Override
    public ResponseEntity<?> deleteAdherant(Long id) {
        Adherant adherant = adherantRepository.findById(id).orElseThrow(
                ()-> new CustomException("Element introuvable")
        );
        adherantRepository.delete(adherant);
        return ResponseEntity.ok("Supression réussie!");
    }

    @Override
    public AdherantResponseDto getAdherantById(Long id) {
        Adherant adherant = adherantRepository.findById(id).orElseThrow(
                ()-> new CustomException("Element introuvable")
        );
        return adherantMapper.adherantToAdherantResponseDto(adherant);
    }

    @Override
    public AdherantResponseDto getAdherantByEmail(String email) {
        Adherant adherant = adherantRepository.findByEmail(email);
        return adherantMapper.adherantToAdherantResponseDto(adherant);
    }

    @Override
    public List<AdherantResponseDto> allAdherants() {
        List<Adherant> adherants= adherantRepository.findAll();
        List<AdherantResponseDto> adherantResponseDtos = adherants.stream().map(
                adherant -> adherantMapper.adherantToAdherantResponseDto(adherant)
        ).collect(Collectors.toList());
        return adherantResponseDtos;
    }

    //---------------------------------------KEYCOAK---------------------------------------

    @Override
    public Response addAdherantToKeycloak(AdherantRequestDto adherantRequestDto) {
        CredentialRepresentation credential=kc.createCredential(adherantRequestDto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(adherantRequestDto.getEmail());
        user.setEmail(adherantRequestDto.getEmail());
        user.setFirstName(adherantRequestDto.getFirstname());
        user.setLastName(adherantRequestDto.getLastName());
        user.setCredentials(Arrays.asList(credential));
        user.setEnabled(true);

        return keycloak.realms().realm("biblio").users().create(user);
    }

    @Override
    public void updateAdherantToKeycloak(AdherantRequestDto adherantRequestDto) {
        UserRepresentation user=keycloak.realm("biblio")
                .users().search(adherantRequestDto.getEmail()).get(0);
        user.setEmail(adherantRequestDto.getEmail());
        user.setUsername(adherantRequestDto.getEmail());
        user.setFirstName(adherantRequestDto.getFirstname());
        user.setLastName(adherantRequestDto.getLastName());
        addRoleToAdherant(user.getUsername(), "APP_ADHERANT");
        keycloak.realm("biblio").users().get(user.getId()).update(user);
    }

    @Override
    public void addRoleToAdherant(String email, String roleName) {
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
    public void deleteAdherantToKeycloak(Optional<Adherant> adherant) {
        UserRepresentation user=keycloak.realm("biblio")
                .users().search(adherant.get().getEmail()).get(0);
        user.setEmail(adherant.get().getEmail());
        user.setUsername(adherant.get().getEmail());
        user.setFirstName(adherant.get().getFirstname());
        keycloak.realm("biblio").users().get(user.getId()).remove();
    }
}
